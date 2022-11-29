package com.cronolytics.api.dto.req;

import java.time.LocalDate;

public class CadastroCandidatoDTO {
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private String telefone;
    private String cep;
    private String estadoCivil;
    private String linkedin;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public String getDescricao() {
        return descricao;
    }
}