package com.cronolytics.api.dto.res;

import java.math.BigInteger;
import java.util.Date;

public class PesquisaMobileDetalhesDTO {

    private Integer id;
    private String nome;
    private Date criada;
    private BigInteger qtdPerguntas;
    private Boolean encerrada;

    public PesquisaMobileDetalhesDTO(Integer id,String nome, Date criada, BigInteger qtdPerguntas, Boolean encerrada) {
        this.id = id;
        this.nome = nome;
        this.criada = criada;
        this.qtdPerguntas = qtdPerguntas;
        this.encerrada = encerrada;
    }

    public PesquisaMobileDetalhesDTO() {

    }

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
