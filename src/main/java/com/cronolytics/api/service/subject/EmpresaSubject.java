package com.cronolytics.api.service.subject;

import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.entity.Pesquisa;
import com.cronolytics.api.service.observer.RespondenteObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpresaSubject {

    private Empresa empresa;
    private  List<Pesquisa> pesquisasAtivas;
    private List<RespondenteObserver> respondentes;
    private Integer idEmpresa;

    public EmpresaSubject(Empresa empresa, List<RespondenteObserver> respondentes, List<Pesquisa> pesquisas) {
        this.empresa = empresa;
        this.respondentes = respondentes;
        this.pesquisasAtivas = pesquisas;
        this.idEmpresa = empresa.getId();
    }

    public EmpresaSubject(Optional<Empresa> empresa){
        this.empresa = empresa.get();
        this.pesquisasAtivas = new ArrayList<>();
        this.respondentes = new ArrayList<>();
        this.idEmpresa = empresa.get().getId();
    }
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<RespondenteObserver> getRespondentes() {
        return respondentes;
    }

    public void setRespondentes(List<RespondenteObserver> respondentes) {
        this.respondentes = respondentes;
    }

    public List<Pesquisa> getPesquisasAtivas() {
        return pesquisasAtivas;
    }

    public void setPesquisasAtivas(List<Pesquisa> pesquisasAtivas) {
        this.pesquisasAtivas = pesquisasAtivas;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public void novaPesquisa(Pesquisa pesquisa){
        pesquisasAtivas.add(pesquisa);
        respondentes.forEach((respondente)->{
                if(!respondente.getPesquisasDisponiveis().contains(pesquisa)){
                    List<Pesquisa> novaLista = respondente.getPesquisasDisponiveis();
                    novaLista.add(pesquisa);
                    respondente.setPesquisasDisponiveis(novaLista);
                }
            }
        );
    }

    public void adicionarRespondente(RespondenteObserver respondente){
        List<Pesquisa> pesquisasRespondente = respondente.getPesquisasDisponiveis();
        pesquisasAtivas.forEach((pesquisa) -> {
                if(!pesquisasRespondente.contains(pesquisa)){
                    pesquisasRespondente.add(pesquisa);
                }
            }
        );
        respondente.setPesquisasDisponiveis(pesquisasRespondente);
        respondentes.add(respondente);
    }

    public void removerRespondente (RespondenteObserver respondente){
        respondentes.forEach((seguidor) -> {
                if(seguidor.getRespondente().getId() == respondente.getRespondente().getId()){
                    seguidor.deixarDeSeguir(idEmpresa);
                    respondentes.remove(seguidor);
                }
            }
        );
    }

    public void encerrarPesquisa (Pesquisa pesquisa){
        pesquisasAtivas.forEach((pesquisaAtiva) -> {
                if(pesquisaAtiva.getId() == pesquisa.getId()){
                    respondentes.forEach((respondente) -> {
                            respondente.encerrarPesquisa(pesquisaAtiva);
                        }
                    );
                    pesquisasAtivas.remove(pesquisaAtiva);
                }
            }
        );
    }
}
