package com.cronolytics.api.dto.res;

import java.math.BigInteger;

public class SeguidoresDTO {
    private Integer id;
    private Integer idEmpresa;
    private BigInteger IdRespondente;

    public SeguidoresDTO(Integer id, Integer idEmpresa, BigInteger idRespondente) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        IdRespondente = idRespondente;
    }

    public SeguidoresDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public BigInteger getIdRespondente() {
        return IdRespondente;
    }

    public void setIdRespondente(BigInteger idRespondente) {
        IdRespondente = idRespondente;
    }
}
