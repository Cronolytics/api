package com.cronolytics.api.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Gabarito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Respondente respondente;
    @OneToMany
    private List<RespostaGabarito> respostasGabarito;
    @ManyToOne
    private Pesquisa pesquisa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Respondente getRespondente() {
        return respondente;
    }

    public void setRespondente(Respondente respondente) {
        this.respondente = respondente;
    }

    public List<RespostaGabarito> getRespostasGabarito() {
        return respostasGabarito;
    }

    public void setRespostasGabarito(List<RespostaGabarito> respostasGabarito) {
        this.respostasGabarito = respostasGabarito;
    }

    public Pesquisa getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }
}
