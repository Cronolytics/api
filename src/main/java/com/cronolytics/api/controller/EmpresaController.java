package com.cronolytics.api.controller;

import com.cronolytics.api.dto.req.CadastroEmpresaDTO;
import com.cronolytics.api.dto.req.LoginDTO;
import com.cronolytics.api.dto.req.SendMailDTO;
import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.repository.IEmpresaRepository;
import com.cronolytics.api.service.MailService;
import com.cronolytics.api.utils.enums.StatusAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    IEmpresaRepository empresaRepository;

    @Autowired
    MailService mailService;

    @PostMapping
    public ResponseEntity cadastro(@RequestBody CadastroEmpresaDTO payload) {
        if (empresaRepository
                .existsByEmailAndCnpj(
                        payload.getEmail(),
                        payload.getCnpj())
        ) return ResponseEntity.status(422).body("E-mail ou CNPJ já cadastrados.");

        Empresa empresa = new Empresa();
        BeanUtils.copyProperties(payload, empresa);

        Empresa saved = empresaRepository.save(empresa);

        byte[] id = String.valueOf(saved.getId()).getBytes();
        String verifyCode = Base64.getEncoder().encodeToString(id);

        SendMailDTO email = new SendMailDTO();
        email.setSubject("Confirmação de e-mail [People Survey]");
        email.setTo(empresa.getEmail());
        email.setText(
                "<h3>Seu código de confirmação:</h3><br>" +
                        "<h1><b>" + verifyCode + "</b></h1><br><br>" +
                        "<p>Caso você desconheça este e-mail, por favor, ignore.</p><br>" +
                        "<p>Atenciosamente, People Survey.</p>");

        try {
            mailService.submit(email);
            return ResponseEntity.status(201).build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Erro ao enviar e-mail de confirmação: " + e.getMessage());
        }
    }


    @PatchMapping("/confirmar-email/{code}")
    public ResponseEntity confirmarEmail(@PathVariable String code) {

        String decoded = new String(Base64.getDecoder().decode(code), StandardCharsets.UTF_8);
        Integer id = Integer.parseInt(decoded);

        Optional<Empresa> u = empresaRepository.findById(id);

        if (u.isPresent()) {
            u.get().setStatus(StatusAccount.VERIFIED);
            empresaRepository.save(u.get());
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(409).body("Código inválido ou usuário inexistente.");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO payload) {

        Empresa empresa = empresaRepository.findByEmailAndSenha(
                payload.getEmail(),
                payload.getSenha()
        );

        if (empresa == null) return ResponseEntity.status(404).build();

        return ResponseEntity.status(200).build();
    }
}
