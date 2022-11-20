package com.cronolytics.api.dto.req;

import com.cronolytics.api.entity.ContaBancaria;
import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.entity.Usuario;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

public class CadastroEmpresaDTO extends Empresa{
    public CadastroEmpresaDTO(String img, String nome, String senha, String email, String cep, String telefone) {
        super(img, nome, senha, email, cep, telefone);
    }

    public CadastroEmpresaDTO(String img, String nome, String senha, String email, String cep, String telefone, Integer id, String cnpj, ContaBancaria contaBancaria) {
        super(img, nome, senha, email, cep, telefone, id, cnpj, contaBancaria);
    }

    public CadastroEmpresaDTO() {
    }
}


