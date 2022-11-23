package com.cronolytics.api.service;

import com.cronolytics.api.entity.Gabarito;
import com.cronolytics.api.entity.Pesquisa;
import com.cronolytics.api.repository.*;
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
    @Autowired
    private IConvidadoRepository convidadoRepository;
    @Autowired
    private IGabaritoRepository gabaritoRepository;

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
    public PesquisaSubject getSubjectPorPesquisa(Pesquisa pesquisa) {
        for (PesquisaSubject subject : subjects) {
            if (subject.getPesquisa().getId().equals(pesquisa.getId())) {
                return subject;
            }
        }
        return null;
    }

    public boolean responderPesquisa(Gabarito gabarito){
        if (gabarito.getConvidado() == null){
            if (gabarito.getRespondente() == null){
                return false;
            }
        }
        if (getSubjectPorPesquisa(gabarito.getPesquisa()) == null){
            return false;
        }
        if (gabarito.getConvidado() != null && gabarito.getRespondente() == null
            && getSubjectPorPesquisa(gabarito.getPesquisa()) != null){
            convidadoRepository.save(gabarito.getConvidado());
            if (!pesquisaRepository.existsById(gabarito.getPesquisa().getId())){
                return false;
            }
            gabaritoRepository.save(gabarito);
            PesquisaSubject pesquisaRespondida = getSubjectPorPesquisa(gabarito.getPesquisa());
            if(pesquisaRespondida.adicionarRespostaEncerrar()){
                encerrarPesquisa(gabarito.getPesquisa());
            }
            return true;
        }
        //if (gabarito.getRespondente() != null)
        return false;
    }
    public void encerrarPesquisa(Pesquisa pesquisa){
        PesquisaSubject pesquisaRespondida = getSubjectPorPesquisa(pesquisa);
        pesquisaRespondida.getPesquisa().setEncerrada(true);
        pesquisaRepository.save(pesquisaRespondida.getPesquisa());
        pesquisaRespondida.removerTodosEmpresa();
        pesquisaRespondida.removerTodos();
        subjects.remove(pesquisaRespondida);
    }
}
