package com.cronolytics.api.dto.req;

public class SeguirDTO {
    private Integer idRespondente;
    private Integer idEmpresa;

    private String nomeEmpresa;

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public SeguirDTO(Integer idRespondente, Integer idEmpresa, String nomeEmpresa) {
        this.idRespondente = idRespondente;
        this.idEmpresa = idEmpresa;
        this.nomeEmpresa = nomeEmpresa;
    }

    public SeguirDTO() {
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Integer getIdRespondente() {
        return idRespondente;
    }

    public void setIdRespondente(Integer idRespondente) {
        this.idRespondente = idRespondente;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
}
