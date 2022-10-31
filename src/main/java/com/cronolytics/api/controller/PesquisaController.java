package com.cronolytics.api.controller;

import com.cronolytics.api.entity.Gabarito;
import com.cronolytics.api.entity.Pesquisa;
import com.cronolytics.api.repository.IPesquisaRepository;
import com.cronolytics.api.repository.IRespondenteRepository;
import com.cronolytics.api.service.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pesquisas")
public class PesquisaController {
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
}
