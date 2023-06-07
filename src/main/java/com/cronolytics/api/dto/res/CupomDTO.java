package com.cronolytics.api.dto.res;

import org.springframework.lang.Nullable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

public class CupomDTO {

    private Long id;
    private String codigo;
    private LocalDate validade;
    private Double valor;
    private Double percentual;
    private Boolean ativo;

    public CupomDTO(Long id,String codigo,LocalDate validade, Double valor, Double percentual, Boolean ativo) {
        this.id = id;
        this.codigo = codigo.toString();
        this.validade = validade;
        this.valor = valor;
        this.percentual = percentual;
        this.ativo = ativo;
    }

    public CupomDTO() {
    }

    public CupomDTO(Long id) {
        this.id = id;
    }

    public CupomDTO(String codigo) {
        this.codigo = codigo;
    }

    public CupomDTO(LocalDate validade) {
        this.validade = validade;
    }

    public CupomDTO(Double valor) {
        this.valor = valor;
    }

    public CupomDTO(Boolean ativo) {
        this.ativo = ativo;
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

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
