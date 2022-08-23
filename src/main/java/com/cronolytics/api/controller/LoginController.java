package com.cronolytics.api.controller;

import com.cronolytics.api.dto.req.LoginDTO;
import com.cronolytics.api.entity.Usuario;
import com.cronolytics.api.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {
    @Autowired
    IUsuarioRepository repository;

    @PostMapping
    public ResponseEntity login(@RequestBody LoginDTO payload) {

        Usuario usuario = repository.findByEmailAndSenha(
                payload.getEmail(),
                payload.getSenha()
        );

        if (usuario == null) return ResponseEntity.status(404).build();

        return ResponseEntity.status(200).build();
    }

}
