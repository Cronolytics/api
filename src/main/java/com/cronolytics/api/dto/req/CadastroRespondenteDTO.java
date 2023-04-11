package com.cronolytics.api.dto.req;

import com.cronolytics.api.entity.Respondente;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

public class CadastroRespondenteDTO extends Respondente{
    public CadastroRespondenteDTO() {
    }

    public CadastroRespondenteDTO(Long id, String genero, String escolaridade, String cpf) {
        super(id, genero, escolaridade, cpf);
    }

    public CadastroRespondenteDTO(String nome, String cpf, String email, String senha){
        super.setNome(nome);
        super.setCpf(cpf);
        super.setEmail(email);
        super.setSenha(senha);
    }

    public CadastroRespondenteDTO(String img, String nome, String senha, String email, String cep, String telefone, Long id, String genero, String escolaridade, String cpf) {
        super(img, nome, senha, email, cep, telefone, id, genero, escolaridade, cpf);
    }
}
