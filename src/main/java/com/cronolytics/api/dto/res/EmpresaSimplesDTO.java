package com.cronolytics.api.dto.res;

import java.math.BigInteger;

public class EmpresaSimplesDTO {
    private Integer idEmpresa;
    private String nomeEmpresa;
    private BigInteger qtdPesq;
    private boolean inscrito;

    public EmpresaSimplesDTO(Integer idEmpresa,String nomeEmpresa, BigInteger qtdPesq, BigInteger inscrito) {
        this.idEmpresa = idEmpresa;
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

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public boolean isInscrito() {
        return inscrito;
    }

    public void setInscrito(boolean inscrito) {
        this.inscrito = inscrito;
    }
}
