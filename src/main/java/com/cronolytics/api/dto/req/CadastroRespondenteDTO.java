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

    public CadastroRespondenteDTO(String img, String nome, String senha, String email, String cep, String telefone, Long id, String genero, String escolaridade, String cpf) {
        super(img, nome, senha, email, cep, telefone, id, genero, escolaridade, cpf);
    }
}
