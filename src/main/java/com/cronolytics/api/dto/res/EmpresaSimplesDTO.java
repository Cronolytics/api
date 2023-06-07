package com.cronolytics.api.dto.res;

import java.math.BigInteger;

public class EmpresaSimplesDTO {
    private String nomeEmpresa;
    private BigInteger qtdPesq;
    private boolean inscrito;

    public EmpresaSimplesDTO(String nomeEmpresa, BigInteger qtdPesq, BigInteger inscrito) {
        this.nomeEmpresa = nomeEmpresa;
        this.qtdPesq = qtdPesq;
        this.inscrito = !Boolean.parseBoolean(inscrito.toString());
    }

    public EmpresaSimplesDTO() {
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public BigInteger getQtdPesq() {
        return qtdPesq;
    }

    public void setQtdPesq(BigInteger qtdPesq) {
        this.qtdPesq = qtdPesq;
    }

    public Boolean getInscrito() {
        return inscrito;
    }

    public void setInscrito(Boolean inscrito) {
        this.inscrito = inscrito;
    }
}
