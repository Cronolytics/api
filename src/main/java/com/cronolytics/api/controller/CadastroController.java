package com.cronolytics.api.controller;

import com.cronolytics.api.dto.req.CadastroDTO;
import com.cronolytics.api.dto.req.SendMailDTO;
import com.cronolytics.api.entity.Usuario;
import com.cronolytics.api.repository.IUsuarioRepository;
import com.cronolytics.api.service.MailService;
import com.cronolytics.api.utils.enums.StatusAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

// TODO
// Implementar JWT
// Adicionar tags de validação nos DTOs


@RestController
@RequestMapping("/cadastro")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CadastroController {

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    MailService mailService;

    @PostMapping
    public ResponseEntity cadastro(@RequestBody CadastroDTO payload) {
        if (usuarioRepository
                .existsByEmailAndCpf(
                payload.getEmail(),
                payload.getCpf())
        ) return ResponseEntity.status(422).body("E-mail ou CPF já cadastrados.");

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(payload, usuario);

        Usuario saved = usuarioRepository.save(usuario);

        byte[] id = String.valueOf(saved.getId()).getBytes();
        String verifyCode = Base64.getEncoder().encodeToString(id);

        SendMailDTO email = new SendMailDTO();
        email.setSubject("Confirmação de e-mail [People Survey]");
        email.setTo(usuario.getEmail());
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
        System.out.println("DECODEEEE: " + decoded);
        Long id = Long.parseLong(decoded);

        Optional<Usuario> u = usuarioRepository.findById(id);

        if (u.isPresent()) {
            u.get().setStatus(StatusAccount.VERIFIED);
            usuarioRepository.save(u.get());
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(409).body("Código inválido ou usuário inexistente.");
    }
}
