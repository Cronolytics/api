package com.cronolytics.api.service.subject;

import com.cronolytics.api.entity.Pesquisa;
import com.cronolytics.api.service.observer.EmpresaObserver;
import com.cronolytics.api.service.observer.RespondenteObserver;

import java.util.ArrayList;
import java.util.List;

public class PesquisaSubject {
    private Pesquisa pesquisa;
    private List<EmpresaObserver> empresas;
    private List<RespondenteObserver> respondentes;
    private Integer respostasTotais;


    PesquisaSubject(Pesquisa pesquisa){
        this.pesquisa=pesquisa;
        this.empresas = new ArrayList<>();
        this.respondentes = new ArrayList<>();
        this.respostasTotais=0;
    }

    public void adicionarEmpresa(EmpresaObserver novaEmpresa){
        if (empresas.stream()
                .noneMatch(empresa ->
                        empresa.getEmpresa().getId()
                                .equals(novaEmpresa.getEmpresa().getId())))
        {
            empresas.add(novaEmpresa);
        }
    }

    public void adicionarRespondentes(RespondenteObserver novoRespondente){
        if (respondentes.stream()
                .noneMatch(respondente ->
                        respondente.getRespondente().getId()
                                .equals(novoRespondente.getRespondente().getId())))
        {
            respondentes.add(novoRespondente);
        }
    }

    public void removerTodos() {
        respondentes.clear();
    }


}
