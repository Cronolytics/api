package com.cronolytics.api.controller;

import com.cronolytics.api.dto.req.LoginDTO;
import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.repository.IEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    @Autowired
    IEmpresaRepository repository;

    @PostMapping
    public ResponseEntity login(@RequestBody LoginDTO payload) {

        Empresa empresa = repository.findByEmailAndSenha(
                payload.getEmail(),
                payload.getSenha()
        );

        if (empresa == null) return ResponseEntity.status(404).build();

        return ResponseEntity.status(200).build();
    }

}
