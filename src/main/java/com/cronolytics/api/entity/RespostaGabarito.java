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
    @ManyToOne
    private Gabarito gabarito;
    private String desc;

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

    public Gabarito getGabarito() {
        return gabarito;
    }

    public void setGabarito(Gabarito gabarito) {
        this.gabarito = gabarito;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
