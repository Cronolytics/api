package com.cronolytics.api.dto.res;

import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;

public class EmpresaDetalhesDTO {
    private String nomeEmpresa;
    private BigInteger qtdInscritos;
    private List<PesquisaMobileDetalhesDTO> pesquisas;

    public EmpresaDetalhesDTO(String nomeEmpresa, BigInteger qtdInscritos, List<PesquisaMobileDetalhesDTO> pesquisas) {
        this.nomeEmpresa = nomeEmpresa;
        this.qtdInscritos = qtdInscritos;
        this.pesquisas = pesquisas;
    }

    public EmpresaDetalhesDTO(String nomeEmpresa, BigInteger qtdInscritos) {
        this.nomeEmpresa = nomeEmpresa;
        this.qtdInscritos = qtdInscritos;
        this.pesquisas = new ArrayList<>();
    }

    public EmpresaDetalhesDTO() {
        this.pesquisas = new ArrayList<>();
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public BigInteger getQtdInscritos() {
        return qtdInscritos;
    }

    public void setQtdInscritos(BigInteger qtdInscritos) {
        this.qtdInscritos = qtdInscritos;
    }

    public List<PesquisaMobileDetalhesDTO> getPesquisas() {
        return pesquisas;
    }

    public void setPesquisas(List<PesquisaMobileDetalhesDTO> pesquisas) {
        this.pesquisas = pesquisas;
    }
}
