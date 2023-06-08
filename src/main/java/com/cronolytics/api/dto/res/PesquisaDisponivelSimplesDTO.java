package com.cronolytics.api.dto.res;

import java.math.BigInteger;

public class PesquisaDisponivelSimplesDTO {
    private Integer id;
    private String nomeEmpresa;
    private String nomePesquisa;
    private Integer limiteRespostas;
    private BigInteger qtdGabaritos;

    public PesquisaDisponivelSimplesDTO(Integer id,String nomeEmpresa, String nomePesquisa, Integer limiteRespostas, BigInteger qtdGabaritos) {
        this.id = id;
        this.nomeEmpresa = nomeEmpresa;
        this.nomePesquisa = nomePesquisa;
        this.limiteRespostas = limiteRespostas;
        this.qtdGabaritos = qtdGabaritos;
    }

    public PesquisaDisponivelSimplesDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomePesquisa() {
        return nomePesquisa;
    }

    public void setNomePesquisa(String nomePesquisa) {
        this.nomePesquisa = nomePesquisa;
    }

    public Integer getLimiteRespostas() {
        return limiteRespostas;
    }

    public void setLimiteRespostas(Integer limiteRespostas) {
        this.limiteRespostas = limiteRespostas;
    }

    public BigInteger getQtdGabaritos() {
        return qtdGabaritos;
    }

    public void setQtdGabaritos(BigInteger qtdGabaritos) {
        this.qtdGabaritos = qtdGabaritos;
    }
}
