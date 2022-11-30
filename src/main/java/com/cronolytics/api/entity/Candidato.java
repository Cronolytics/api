package com.cronolytics.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Candidato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private String telefone;
    private String cep;
    private String estadoCivil;
    private String instituicaoDeEnsino;
    private String vaga;
    private String grauDeEnsino;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Candidato(
                     String nome,
                     String cpf,
                     String email,
                     LocalDate dataNascimento,
                     String telefone,
                     String cep,
                     String estadoCivil,
                     String instituicaoDeEnsino,
                     String vaga,
                     String grauDeEnsino) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.cep = cep;
        this.estadoCivil = estadoCivil;
        this.instituicaoDeEnsino = instituicaoDeEnsino;
        this.vaga = vaga;
        this.grauDeEnsino = grauDeEnsino;
    }

    public Candidato(Integer id,
                     String nome,
                     String cpf,
                     String email,
                     LocalDate dataNascimento,
                     String telefone,
                     String cep,
                     String estadoCivil,
                     String instituicaoDeEnsino,
                     String vaga,
                     String grauDeEnsino) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.cep = cep;
        this.estadoCivil = estadoCivil;
        this.instituicaoDeEnsino = instituicaoDeEnsino;
        this.vaga = vaga;
        this.grauDeEnsino = grauDeEnsino;
    }

    public Candidato(){}

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getInstituicaoDeEnsino() {
        return instituicaoDeEnsino;
    }

    public void setInstituicaoDeEnsino(String instituicaoDeEnsino) {
        this.instituicaoDeEnsino = instituicaoDeEnsino;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String descricao) {
        this.vaga = descricao;
    }

    public String getGrauDeEnsino() {
        return grauDeEnsino;
    }

    public void setGrauDeEnsino(String grauDeEnsino) {
        this.grauDeEnsino = grauDeEnsino;
    }
}