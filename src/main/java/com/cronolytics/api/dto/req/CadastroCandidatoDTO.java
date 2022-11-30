package com.cronolytics.api.dto.req;

import com.cronolytics.api.entity.Candidato;

import java.time.LocalDate;

public class CadastroCandidatoDTO extends Candidato {
    public CadastroCandidatoDTO(String nome, String cpf, String email, LocalDate dataNascimento, String telefone, String cep, String estadoCivil, String instituicaoDeEnsino, String vaga, String grauDeEnsino) {
        super(nome, cpf, email, dataNascimento, telefone, cep, estadoCivil, instituicaoDeEnsino, vaga, grauDeEnsino);
    }
}