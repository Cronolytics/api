package com.cronolytics.api.service.observer;

import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.repository.IEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EmpresaObserver {

    private IEmpresaRepository empresaRepository;
    private Integer idUsuario;

    public EmpresaObserver(Empresa empresa,IEmpresaRepository empresaRepository){
        this.idUsuario = empresa.getId();
        this.empresaRepository=empresaRepository;
    }

    public Empresa getEmpresa(){
        return empresaRepository.findById(idUsuario).get();
    }


}
