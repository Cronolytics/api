package com.cronolytics.api.controller;

import com.cronolytics.api.repository.IPesquisaRepository;
import com.cronolytics.api.service.PesquisaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pesquisas")
public class PesquisaController {
    @Autowired
    IPesquisaRepository repository;
    @Autowired
    PesquisaService service;
}
