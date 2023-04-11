package com.cronolytics.api.entity;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Transactional
public class Seguidores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Respondente respondente;

    @ManyToOne
    private Empresa empresa;

    public Seguidores(Respondente idRespondente, Empresa idEmpresa) {
        this.respondente = idRespondente;
        this.empresa = idEmpresa;
    }

    public Seguidores(Integer id, Respondente idRespondente, Empresa idEmpresa) {
        this.id = id;
        this.respondente = idRespondente;
        this.empresa = idEmpresa;
    }

    public Seguidores() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Respondente getRespondente() {
        return respondente;
    }

    public void setRespondente(Respondente idRespondente) {
        this.respondente = idRespondente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa idEmpresa) {
        this.empresa = idEmpresa;
    }
}
