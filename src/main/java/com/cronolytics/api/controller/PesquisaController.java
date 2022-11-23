package com.cronolytics.api.controller;

import com.cronolytics.api.entity.Gabarito;
import com.cronolytics.api.entity.Pesquisa;
import com.cronolytics.api.repository.IEmpresaRepository;
import com.cronolytics.api.repository.IPesquisaRepository;
import com.cronolytics.api.repository.IRespondenteRepository;
import com.cronolytics.api.service.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pesquisas")
public class PesquisaController {
    @Autowired
    IEmpresaRepository empresaRepository;
    @Autowired
    IPesquisaRepository pesquisaRepository;
    @Autowired
    PesquisaService service;
    @Autowired
    IRespondenteRepository respondenteRepository;

    @PostMapping("/gravar")
    public ResponseEntity<Pesquisa> salvarPesquisa(@RequestBody Pesquisa pesquisa){
        service.salvar(pesquisa);
        return ResponseEntity.status(201).body(pesquisa);
    }

    @PostMapping("/responder")
    public ResponseEntity responder(@RequestBody Gabarito gabarito){
        return service.responderPesquisa(gabarito) ?
                ResponseEntity.status(201).body(gabarito) :
                ResponseEntity.status(404).build();
    }

    @GetMapping
    public ResponseEntity getPesquisa(@RequestParam(required = true) Integer idPesquisa){
//        if(!pesquisaRepository.existsById(idPesquisa)){
//            return ResponseEntity.status(404).build();
//        }
        Optional<Pesquisa> pesquisa = pesquisaRepository.findById(idPesquisa);
        return ResponseEntity.status(200).body(pesquisa);
    }

    @GetMapping("/pesquisas-simples")
    public ResponseEntity pesquisasSimples(@RequestParam(required = true) Integer idEmpresa){
        if(!empresaRepository.existsById(idEmpresa)){
            return ResponseEntity.status(404).build();
        }
        List<Optional<Pesquisa>> pesquisas = pesquisaRepository.findAllByEmpresaId(idEmpresa);
        if (pesquisas.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).build();
    }
}
