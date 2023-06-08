package com.cronolytics.api.controller;

import com.cronolytics.api.dto.req.CadastroRespondenteDTO;
import com.cronolytics.api.dto.req.LoginDTO;
import com.cronolytics.api.dto.req.SeguirDTO;
import com.cronolytics.api.dto.req.SendMailDTO;
import com.cronolytics.api.dto.res.*;
import com.cronolytics.api.entity.*;
import com.cronolytics.api.repository.*;
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

    @Autowired
    ICupomRepository cupomRepository;

    @Autowired
    IGabaritoRepository gabaritoRepository;

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
            SeguirDTO seguirDTO = new SeguirDTO();
            return ResponseEntity.status(200).body(seguirDTO);
        }
        Empresa empresa = new Empresa();
        Respondente respondente = new Respondente();
        empresa.setId(idEmpresa);
        respondente.setId(idRespondente.longValue());
        Seguidores seguir = new Seguidores(respondente,empresa);
        seguidoresRepository.save(seguir);
        SeguirDTO seguindo = new SeguirDTO(idRespondente,idEmpresa,empresaRepository.findNomeById(idEmpresa).get());
        return ResponseEntity.status(201).body(seguindo);
    }

    @GetMapping("/empresas-ativas")
    public ResponseEntity empresasAtivas(@RequestParam Integer idRespondente){
        List<Optional<EmpresaSimplesDTO>> empresas = empresaRepository.EmpresaSimplesDTOByIdRespondente(idRespondente.intValue());
        empresas.forEach((empresa) -> {empresa.get().setInscrito(seguidoresRepository.existsByRespondenteIdAndEmpresaId(idRespondente.longValue(), empresa.get().getIdEmpresa()));});
        return !empresas.isEmpty() ? ResponseEntity.status(200).body(empresas) : ResponseEntity.status(204).build();
    }

    @GetMapping("/pesquisas-empresa")
    public ResponseEntity pesquisasExternas(@RequestParam(required = true) Integer idEmpresa){
        List<Optional<PesquisaMobileDetalhesDTO>> pesquisas = pesquisaRepository.PesquisaMobileDetalhesDTOByIdEmpresa(idEmpresa);
        return !pesquisas.isEmpty() ? ResponseEntity
                .status(200)
                .body(pesquisas) : ResponseEntity
                .status(204)
                .build();
    }

    @GetMapping("/minhas-pesquisas")
    public ResponseEntity minhasPesquisas(@RequestParam Integer idRespondente){
        List<Optional<Seguidores>> seguindo = seguidoresRepository.findByRespondenteId(idRespondente.longValue());
        if(seguindo.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        List<Integer> idsEmpresas = new ArrayList<>();
        seguindo.forEach((segue) -> {idsEmpresas.add(segue.get().getEmpresa().getId());});
        List<Optional<PesquisaDisponivelSimplesDTO>> pesquisas = pesquisaRepository.PesquisaDisponivelSimplesDTOByIdEmpresa(idsEmpresas);
        if(pesquisas.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(pesquisas);
    }

    @GetMapping("/meus-cupons")
    public ResponseEntity meusCupons(@RequestParam Integer idRespondente){
        if(!respondenteRepository.existsById(idRespondente.longValue())){
            return ResponseEntity.status(404).build();
        }
        List<Optional<Cupom>> cupons = cupomRepository.cupomPorIdRespondente(idRespondente.longValue());
        for (int i = 0; i < cupons.size() - 1; i++) {
            for (int j = 0; j < cupons.size() - 1; j++) {
                if (cupons.get(i).get().getId() == cupons.get(j).get().getId() && j != i) {
                    cupons.remove(j);
                }
                for (int k = 0; k < cupons.size() - 1; k++) {
                    if (cupons.get(j).get().getId() == cupons.get(k).get().getId() && k != j) {
                        cupons.remove(k);
                    }
                    for (int l = cupons.size() - 1; l > 0; l--) {
                        if (cupons.get(i).get().getId() == cupons.get(l).get().getId() && l != i) {
                            cupons.remove(l);
                        }
                    }
                }
            }
        }
        return !cupons.isEmpty() ? ResponseEntity
                .status(200)
                .body(cupons) : ResponseEntity
                .status(204)
                .build();
    }

    @GetMapping("/empresa")
    public ResponseEntity verEmpresa(@RequestParam Integer idEmpresa){
        if(!empresaRepository.existsById(idEmpresa)){
            return ResponseEntity.status(404).build();
        }
        EmpresaDetalhesDTO empresa = new EmpresaDetalhesDTO();
        empresa.setNomeEmpresa(empresaRepository.findNomeById(idEmpresa).get());
        empresa.setQtdInscritos(seguidoresRepository.countByEmpresaId(idEmpresa));
        List<Optional<PesquisaMobileDetalhesDTO>> pesquisas = pesquisaRepository.PesquisaMobileDetalhesDTOByIdEmpresa(idEmpresa);
        if(pesquisas.isEmpty()){
            return ResponseEntity.status(200).body(empresa);
        }
        pesquisas.forEach((pesquisa) -> {empresa.getPesquisas().add(pesquisa.get());});
        return ResponseEntity.status(200).body(empresa);
    }

    @PatchMapping("/invalidar-cupom")
    public ResponseEntity invalidarCupom(@RequestParam Integer idCupom){
        if(!cupomRepository.existsById(idCupom.longValue())){
            return ResponseEntity.status(404).build();
        }
        cupomRepository.updateCupomById(idCupom.longValue());
        return ResponseEntity.status(200).build();
    }
}
