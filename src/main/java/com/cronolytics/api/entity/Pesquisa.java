package com.cronolytics.api.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pesquisa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descri;
    private boolean exploratoria;
    private boolean encerrada;
    private Integer participantesAlvo;
    private boolean interna;
    @ManyToOne
    private Empresa empresa;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "id_pesquisa")
    private List<Pergunta> perguntas = new ArrayList<>();

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

    public String getDesc() {
        return descri;
    }

    public void setDesc(String desc) {
        this.descri = desc;
    }

    public boolean getExploratoria() {
        return exploratoria;
    }

    public void setExploratoria(boolean exploratoria) {
        this.exploratoria = exploratoria;
    }

    public boolean getEncerrada() {
        return encerrada;
    }

    public void setEncerrada(boolean encerrada) {
        this.encerrada = encerrada;
    }

    public Integer getParticipantesAlvo() {
        return participantesAlvo;
    }

    public void setParticipantesAlvo(Integer participantesAlvo) {
        this.participantesAlvo = participantesAlvo;
    }
    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public boolean isExploratoria() {
        return exploratoria;
    }

    public boolean isEncerrada() {
        return encerrada;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean isInterna() {
        return interna;
    }

    public void setInterna(boolean interna) {
        this.interna = interna;
    }
}
