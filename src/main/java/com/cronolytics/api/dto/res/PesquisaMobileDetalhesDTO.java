package com.cronolytics.api.dto.res;

import java.math.BigInteger;
import java.util.Date;

public class PesquisaMobileDetalhesDTO {

    private Integer id;
    private String nome;
    private Date criada;
    private BigInteger qtdPerguntas;
    private Boolean encerrada;
    private Boolean respondida;

    public PesquisaMobileDetalhesDTO(Integer id,String nome, Date criada, BigInteger qtdPerguntas, Boolean encerrada, String respondida) {
        this.id = id;
        this.nome = nome;
        this.criada = criada;
        this.qtdPerguntas = qtdPerguntas;
        this.encerrada = encerrada;
        this.respondida = Boolean.parseBoolean(respondida);
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

    public Boolean getRespondida() {
        return respondida;
    }

    public void setRespondida(Boolean respondida) {
        this.respondida = respondida;
    }
}
