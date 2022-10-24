package com.cronolytics.api.service;

import com.cronolytics.api.entity.Gabarito;
import com.cronolytics.api.entity.Pesquisa;
import com.cronolytics.api.repository.IEmpresaRepository;
import com.cronolytics.api.repository.IPesquisaRepository;
import com.cronolytics.api.repository.IRespondenteRepository;
import com.cronolytics.api.service.subject.PesquisaSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PesquisaService {
    @Autowired
    private IPesquisaRepository repository;
    private List<PesquisaSubject> subjects = new ArrayList<>();
    @Autowired
    private IEmpresaRepository empresaRepository;
    @Autowired
    private IPesquisaRepository pesquisaRepository;
    @Autowired
    private IRespondenteRepository respondenteRepository;

    public Pesquisa salvar(Pesquisa pesquisa){
    if(pesquisa.getId()==null){
        registrarPesquisa(pesquisa);
    }
    pesquisaRepository.save(pesquisa);
    return pesquisa;
    }
    public void registrarPesquisa(Pesquisa pesquisa){
        subjects.add(new PesquisaSubject(pesquisa));
    }
    private PesquisaSubject getSubjectPorPesquisa(Pesquisa pesquisa) {
        for (PesquisaSubject subject : subjects) {
            if (subject.getPesquisa().getId().equals(pesquisa.getId())) {
                return subject;
            }
        }
        return null;
    }

    private boolean responderPesquisa(Gabarito gabarito,Integer idPesquisa){

    }
}
