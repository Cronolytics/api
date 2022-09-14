package com.cronolytics.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String digito;
    private String agencia;
    private String pix;

    public ContaBancaria() {}

    public ContaBancaria(Long id, String numero, String digito, String agencia, String pix) {
        this.numero = numero;
        this.digito = digito;
        this.agencia = agencia;
        this.pix = pix;
    }

    public ContaBancaria(String numero, String digito, String agencia, String pix) {
        this.numero = numero;
        this.digito = digito;
        this.agencia = agencia;
        this.pix = pix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDigito() {
        return digito;
    }

    public void setDigito(String digito) {
        this.digito = digito;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
    }
}
