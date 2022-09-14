package com.cronolytics.api.entity;

import com.cronolytics.api.utils.enums.StatusAccount;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Usuario {
    private String img;
    private String nome;
    private String senha;
    private String email;
    private String cep;
    private String telefone;
    private StatusAccount status;

    public Usuario() {
    }

    public Usuario(String img, String nome, String senha, String email, String cep, String telefone) {
        this.img = img;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.cep = cep;
        this.telefone = telefone;
        this.status = StatusAccount.PENDING;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public StatusAccount getStatus() {
        return status;
    }

    public void setStatus(StatusAccount status) {
        this.status = status;
    }
}
