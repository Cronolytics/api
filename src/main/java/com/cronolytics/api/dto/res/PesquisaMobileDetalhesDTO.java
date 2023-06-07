package com.cronolytics.api.dto.res;

import java.math.BigInteger;
import java.util.Date;

public class PesquisaMobileDetalhesDTO {
    private String nome;
    private Date criada;
    private BigInteger qtdPerguntas;
    private Boolean encerrada;

    public PesquisaMobileDetalhesDTO(String nome, Date criada, BigInteger qtdPerguntas, Boolean encerrada) {
        this.nome = nome;
        this.criada = criada;
        this.qtdPerguntas = qtdPerguntas;
        this.encerrada = encerrada;
    }

    public PesquisaMobileDetalhesDTO() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getCriada() {
        return criada;
    }

    public void setCriada(Date criada) {
        this.criada = criada;
    }

    public BigInteger getQtdPerguntas() {
        return qtdPerguntas;
    }

    public void setQtdPerguntas(BigInteger qtdPerguntas) {
        this.qtdPerguntas = qtdPerguntas;
    }

    public Boolean getEncerrada() {
        return encerrada;
    }

    public void setEncerrada(Boolean encerrada) {
        this.encerrada = encerrada;
    }
}
