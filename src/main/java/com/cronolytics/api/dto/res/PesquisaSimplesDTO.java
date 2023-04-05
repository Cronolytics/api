package com.cronolytics.api.dto.res;

import java.math.BigInteger;

public class PesquisaSimplesDTO {
    private int id;
    private boolean selecionado;
    private String nome;
    private int qtdPerguntas;
    private int qtdRespostas;
    private int qtdPessoas;
    private boolean ativa;

    private boolean interna;

    private boolean exploratoria;

    public PesquisaSimplesDTO(Integer id, String nome, BigInteger qtdPerguntas, Boolean ativa, Boolean interna, Boolean exploratoria) {
        this.id = id.intValue();
        this.selecionado = selecionado;
        this.nome = nome;
        this.qtdPerguntas = qtdPerguntas.intValue();
        this.qtdPessoas = qtdPessoas;
        //this.qtdRespostas = qtdPerguntas * qtdPessoas;
        this.ativa = !ativa.booleanValue();
        this.interna = interna.booleanValue();
        this.exploratoria = exploratoria.booleanValue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdPerguntas() {
        return qtdPerguntas;
    }

    public void setQtdPerguntas(int qtdPerguntas) {
        this.qtdPerguntas = qtdPerguntas;
    }

    public int getQtdRespostas() {
        return qtdRespostas;
    }

    public void setQtdRespostas(int qtdRespostas) {
        this.qtdRespostas = qtdRespostas;
    }

    public int getQtdPessoas() {
        return qtdPessoas;
    }

    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
        this.qtdRespostas = qtdPerguntas * qtdPessoas;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public boolean isInterna() {
        return interna;
    }

    public void setInterna(boolean interna) {
        this.interna = interna;
    }

    public boolean isExploratoria() {
        return exploratoria;
    }

    public void setExploratoria(boolean exploratoria) {
        this.exploratoria = exploratoria;
    }
}
