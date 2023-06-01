package com.cronolytics.api.dto.res;

public class PesquisaDisponivelSimplesDTO {
    private String nomeEmpresa;
    private String nomePesquisa;
    private Integer qtdDesconto;
    private Integer limiteRespostas;
    private Integer qtdGabaritos;

    public PesquisaDisponivelSimplesDTO(String nomeEmpresa, String nomePesquisa, Integer qtdDesconto, Integer limiteRespostas, Integer qtdGabaritos) {
        this.nomeEmpresa = nomeEmpresa;
        this.nomePesquisa = nomePesquisa;
        this.qtdDesconto = qtdDesconto;
        this.limiteRespostas = limiteRespostas;
        this.qtdGabaritos = qtdGabaritos;
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

    public Integer getQtdDesconto() {
        return qtdDesconto;
    }

    public void setQtdDesconto(Integer qtdDesconto) {
        this.qtdDesconto = qtdDesconto;
    }

    public Integer getLimiteRespostas() {
        return limiteRespostas;
    }

    public void setLimiteRespostas(Integer limiteRespostas) {
        this.limiteRespostas = limiteRespostas;
    }

    public Integer getQtdGabaritos() {
        return qtdGabaritos;
    }

    public void setQtdGabaritos(Integer qtdGabaritos) {
        this.qtdGabaritos = qtdGabaritos;
    }
}
