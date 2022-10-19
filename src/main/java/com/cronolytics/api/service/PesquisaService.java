package com.cronolytics.api.service;

import com.cronolytics.api.repository.IPesquisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PesquisaService {
    @Autowired
    IPesquisaRepository repository;
}
