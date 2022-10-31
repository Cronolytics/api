package com.cronolytics.api.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
public class Gabarito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @Nullable
    private Convidado convidado;
    @OneToOne
    @Nullable
    private Respondente respondente;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "id_gabarito")
    private List<RespostaGabarito> respostasGabarito;
    @ManyToOne
    private Pesquisa pesquisa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Nullable
    public Respondente getRespondente() {
        return respondente;
    }

    public void setRespondente(@Nullable Respondente respondente) {
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
    @Nullable
    public Convidado getConvidado() {
        return convidado;
    }

    public void setConvidado(@Nullable Convidado convidado) {
        this.convidado = convidado;
    }
}
