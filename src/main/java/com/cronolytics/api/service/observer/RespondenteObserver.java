package com.cronolytics.api.service.observer;

import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.entity.Respondente;
import com.cronolytics.api.repository.IEmpresaRepository;
import com.cronolytics.api.repository.IRespondenteRepository;

public class RespondenteObserver {

    private IRespondenteRepository respondenteRepository;
    private Long idUsuario;

    public RespondenteObserver(Respondente respondente, IRespondenteRepository empresaRepository){
        this.idUsuario = respondente.getId();
        this.respondenteRepository=empresaRepository;
    }

    public Respondente getRespondente(){
        return respondenteRepository.findById(idUsuario).get();
    }


}
