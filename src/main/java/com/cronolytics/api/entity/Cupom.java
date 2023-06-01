package com.cronolytics.api.entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Cupom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private LocalDate validade;
    @Nullable
    private Double valor;
    @Nullable
    private Double percentual;
    private Boolean ativo;

    @OneToOne
    private Gabarito gabarito;

    public Cupom() {
        this.codigo = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo.toString();
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Nullable
    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(@Nullable Double percentual) {
        this.percentual = percentual;
    }

    public Gabarito getGabarito() {
        return gabarito;
    }

    public void setGabarito(Gabarito gabarito) {
        this.gabarito = gabarito;
    }
}
