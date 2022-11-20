package com.cronolytics.api.entity;

import com.cronolytics.api.utils.enums.StatusAccount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Respondente extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genero;
    private String escolaridade;
    private String cpf;

    private StatusAccount statusRespondente;

    public Respondente(){super();}

    public Respondente(Long id, String genero, String escolaridade, String cpf) {
        this.id = id;
        this.genero = genero;
        this.escolaridade = escolaridade;
        this.cpf = cpf;
    }

    public Respondente(String img, String nome, String senha, String email, String cep, String telefone, Long id, String genero, String escolaridade, String cpf) {
        super(img, nome, senha, email, cep, telefone);
        this.id = id;
        this.genero = genero;
        this.escolaridade = escolaridade;
        this.cpf = cpf;
        this.statusRespondente= StatusAccount.PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha(){
        return super.getSenha();
    }

    public void setSenha(String senha){
        super.setSenha(senha);
    }

    public String getEmail(){
        return super.getEmail();
    }

    public void setEmail(String email){
        super.setEmail(email);
    }

    public StatusAccount getStatusRespondente() {
        return statusRespondente;
    }

    public void setStatusRespondente(StatusAccount statusRespondente) {
        this.statusRespondente = statusRespondente;
    }
}
