package com.cronolytics.api.controller;

import com.cronolytics.api.dto.req.CadastroRespondenteDTO;
import com.cronolytics.api.dto.req.LoginDTO;
import com.cronolytics.api.dto.req.SeguirDTO;
import com.cronolytics.api.dto.req.SendMailDTO;
import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.entity.Pesquisa;
import com.cronolytics.api.entity.Respondente;
import com.cronolytics.api.entity.Seguidores;
import com.cronolytics.api.repository.IEmpresaRepository;
import com.cronolytics.api.repository.IPesquisaRepository;
import com.cronolytics.api.repository.IRespondenteRepository;
import com.cronolytics.api.repository.ISeguidoresRepository;
import com.cronolytics.api.service.MailService;
import com.cronolytics.api.service.PesquisaService;
import com.cronolytics.api.utils.enums.FilaObj;
import com.cronolytics.api.utils.enums.ListaObj;
import com.cronolytics.api.utils.enums.StatusAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respondente")
public class RespondenteController {
    @Autowired
    IRespondenteRepository respondenteRepository;

    @Autowired
    MailService mailService;

    @Autowired
    PesquisaService pesquisaService;

    @Autowired
    IEmpresaRepository empresaRepository;

    @Autowired
    IPesquisaRepository pesquisaRepository;

    @Autowired
    ISeguidoresRepository seguidoresRepository;

    FilaObj<CadastroRespondenteDTO> filaRespondente = new FilaObj<>(10);

    @GetMapping("/listar-respondentes")
    public ResponseEntity<ListaObj<Respondente>> listarTodos() {
        List<Respondente> lista = respondenteRepository.findAll();

        if (lista.isEmpty()) return ResponseEntity.status(204).build();

        ListaObj<Respondente> listaObj = new ListaObj<Respondente>(lista.size());

        for (Respondente r : lista) {
            listaObj.adiciona(r);
        }

        return ResponseEntity.ok(listaObj);
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody CadastroRespondenteDTO respondenteDTO) {

        filaRespondente.insert(respondenteDTO);

        CadastroRespondenteDTO payload = filaRespondente.poll();

        if(respondenteRepository.existsByEmailOrCpf(
                payload.getEmail(),
                payload.getCpf()))
            return  ResponseEntity.status(422).body("E-mail ou CPF já cadastrados.");

        Respondente respondente = new Respondente();
        BeanUtils.copyProperties(payload,respondente);

        Respondente saved = respondenteRepository.save(respondente);

        byte[] id = String.valueOf(saved.getId()).getBytes();
        String verifyCode = Base64.getEncoder().encodeToString(id);

        SendMailDTO email = new SendMailDTO();
        email.setSubject("Confirmação de e-mail [People Survey]");
        email.setTo(respondente.getEmail());
        email.setText(
                "<h3>Seu código de confirmação:</h3><br>" +
                        "<h1><b>" + verifyCode + "</b></h1><br><br>" +
                        "<p>Caso você desconheça este e-mail, por favor, ignore.</p><br>" +
                        "<p>Atenciosamente, People Survey.</p>");

        try {
            //mailService.submit(email);
            return ResponseEntity.status(201).body(saved);
        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body("Erro ao enviar e-mail de confirmação: " + e.getMessage());
        }
    }

    @PatchMapping("/confirmar-email/{code}")
    public ResponseEntity confirmarEmail(@PathVariable String code) {

        String decoded = new String(Base64.getDecoder().decode(code), StandardCharsets.UTF_8);
        Long id = Long.parseLong(decoded);

        Optional<Respondente> respondente = respondenteRepository.findById(id);

        if (respondente.isPresent()) {
            respondente.get().setStatusRespondente(StatusAccount.VERIFIED);
            respondenteRepository.save(respondente.get());
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(409).body("Código inválido ou usuário inexistente.");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO payload) {

        Optional<Respondente> respondente = respondenteRepository.findByEmailAndSenha(
                payload.getEmail(),
                payload.getSenha()
        );

        if (respondente.isEmpty())
        {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(respondente);
    }

    @PostMapping("/inscricao")
    public ResponseEntity inscricao(
            @RequestParam Integer idRespondente, @RequestParam Integer idEmpresa){
        if(!empresaRepository.existsById(idEmpresa)||!respondenteRepository.existsById(idRespondente.longValue())){
            return ResponseEntity.status(404).build();
        }
        if (seguidoresRepository.existsByRespondenteIdAndEmpresaId(idRespondente.longValue(),idEmpresa)){
            seguidoresRepository.deleteByRespondenteIdAndEmpresaId(idRespondente.longValue(),idEmpresa);
            return ResponseEntity.status(200).build();
        }
        Empresa empresa = new Empresa();
        Respondente respondente = new Respondente();
        empresa.setId(idEmpresa);
        respondente.setId(idRespondente.longValue());
        Seguidores seguir = new Seguidores(respondente,empresa);
        seguidoresRepository.save(seguir);
        return ResponseEntity.status(201).body(seguir);
    }

    @GetMapping("/empresas-ativas")
    public ResponseEntity empresasAtivas(){
        List<Empresa> empresas = empresaRepository.findAll();
        return !empresas.isEmpty() ? ResponseEntity.status(200).body(empresas) : ResponseEntity.status(204).build();
    }

    @GetMapping("/pesquisas-empresa")
    public ResponseEntity pesquisasExternas(@RequestParam(required = true) Integer idEmpresa){
        List<Optional<Pesquisa>> pesquisas = pesquisaRepository.findAllByEncerradaFalseAndInternaFalse();
        List<Optional<Pesquisa>> pesquisasEsp = new ArrayList<>();
        pesquisas.forEach((pesquisa) -> {
                if(pesquisa.get().getEmpresa().getId() == idEmpresa){
                    pesquisasEsp.add(pesquisa);
                }
            }
        );
        return !pesquisasEsp.isEmpty() ? ResponseEntity
                .status(200)
                .body(pesquisasEsp) : ResponseEntity
                .status(204)
                .build();
    }

    @GetMapping("/minhas-pesquisas")
    public ResponseEntity minhasPesquisas(@RequestParam Integer idRespondente){
        List<Pesquisa> pesquisas = new ArrayList<>();
        Optional<List<Seguidores>> seguindo = seguidoresRepository.findByRespondenteId(idRespondente.longValue());
        List<Empresa> empresas = new ArrayList<>();
        seguindo.get().forEach((seguido) -> {empresas.add(empresaRepository.findById(seguido.getEmpresa().getId().intValue()).get());});
        empresas.forEach((empresa) -> {List<Optional<Pesquisa>> pesquisasAtivas = pesquisaRepository.findByEmpresaIdAndEncerradaFalse(empresa.getId());
        pesquisasAtivas.forEach((pesquisaAtiva) -> {pesquisas.add(pesquisaAtiva.get());});
        });
        return !pesquisas.isEmpty() ? ResponseEntity.status(200).body(pesquisas) : ResponseEntity.status(204).build();
    }
}
