package com.cronolytics.api.dto.req;

import java.time.LocalDate;

public class CadastroDTO {
    private String img;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private String cep;
    private LocalDate nascimento;

    public String getImg() {
        return img;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCep() {
        return cep;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }
}
