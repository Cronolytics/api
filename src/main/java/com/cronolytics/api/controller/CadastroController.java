package com.cronolytics.api.controller;

import com.cronolytics.api.dto.req.CadastroDTO;
import com.cronolytics.api.entity.Usuario;
import com.cronolytics.api.repository.IUsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CadastroController {

    @Autowired
    IUsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity cadastro(@RequestBody CadastroDTO payload) {

        if (usuarioRepository.existsByEmail(payload.getEmail())) {
            return ResponseEntity.status(422).build();
        }

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(payload, usuario);


        // TODO
        // Pensar em um jeito de gerar um código de verificação
        // Enviar o código por e-mail
        // Criar um endpoint para confirmar o código
        // Implementar JWT
        
        // Usuario saved = usuarioRepository.save(usuario);

        return ResponseEntity.status(201).build();
    }
}
