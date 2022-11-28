package com.cronolytics.api.dto.res;

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

    public PesquisaSimplesDTO(int id,  String nome, int qtdPerguntas, int qtdPessoas, boolean ativa, boolean interna, boolean exploratoria) {
        this.id = id;
        this.selecionado = selecionado;
        this.nome = nome;
        this.qtdPerguntas = qtdPerguntas;
        this.qtdPessoas = qtdPessoas;
        this.qtdRespostas = qtdPerguntas * qtdPessoas;
        this.ativa = !ativa;
        this.interna = interna;
        this.exploratoria = exploratoria;
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
