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
    private String desc;
    private Boolean exploratoria;
    private Boolean encerrada;
    private Integer participantesAlvo;
    @OneToMany
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
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getExploratoria() {
        return exploratoria;
    }

    public void setExploratoria(Boolean exploratoria) {
        this.exploratoria = exploratoria;
    }

    public Boolean getEncerrada() {
        return encerrada;
    }

    public void setEncerrada(Boolean encerrada) {
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
}
