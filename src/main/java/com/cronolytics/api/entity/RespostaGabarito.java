package com.cronolytics.api.entity;

import javax.persistence.*;

@Entity
public class RespostaGabarito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Pergunta pergunta;
    @OneToOne
    private Resposta resposta;
    private String descri;

    private Integer ranking;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }

    public String getDesc() {
        return descri;
    }

    public void setDesc(String desc) {
        this.descri = desc;
    }

    public Integer getRank() {
        return ranking;
    }

    public void setRank(Integer rank) {
        this.ranking = rank;
    }
}
