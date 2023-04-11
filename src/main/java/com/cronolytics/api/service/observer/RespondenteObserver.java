package com.cronolytics.api.service.observer;

import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.entity.Pesquisa;
import com.cronolytics.api.entity.Respondente;
import com.cronolytics.api.repository.IRespondenteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RespondenteObserver {
    @Autowired
    private IRespondenteRepository respondenteRepository;
    private Long idUsuario;

    private List<Pesquisa> pesquisasDisponiveis;

    public RespondenteObserver(Respondente respondente){
        this.idUsuario = respondente.getId();
        this.pesquisasDisponiveis = new ArrayList<>();
    }

    public Respondente getRespondente(){
        return respondenteRepository.findById(idUsuario).get();
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Pesquisa> getPesquisasDisponiveis() {
        return pesquisasDisponiveis;
    }

    public void setPesquisasDisponiveis(List<Pesquisa> pesquisasDisponiveis) {
        this.pesquisasDisponiveis = pesquisasDisponiveis;
    }

    public void deixarDeSeguir(Integer idEmpresa){
        pesquisasDisponiveis.forEach((pesquisa) -> {
                if(pesquisa.getEmpresa().getId() == idEmpresa){
                    pesquisasDisponiveis.remove(pesquisa);
                }
            }
        );
    }

    public void encerrarPesquisa(Pesquisa pesquisa){
        pesquisasDisponiveis.remove(pesquisa);
    }
}
