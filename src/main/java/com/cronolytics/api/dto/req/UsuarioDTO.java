package com.cronolytics.api.dto.req;

import com.cronolytics.api.entity.Usuario;

public class UsuarioDTO extends Usuario {
    public UsuarioDTO(String img, String nome, String senha, String email, String cep, String telefone) {
        super(img, nome, senha, email, cep, telefone);
    }

    @Override
    public String toString() {
        return "UsuarioDTO{} " + super.toString();
    }
}
