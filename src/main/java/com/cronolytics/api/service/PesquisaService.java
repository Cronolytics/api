package com.cronolytics.api.service;

import com.cronolytics.api.entity.*;
import com.cronolytics.api.repository.*;
import com.cronolytics.api.service.observer.RespondenteObserver;
import com.cronolytics.api.service.subject.EmpresaSubject;
import com.cronolytics.api.service.subject.PesquisaSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PesquisaService {
    @Autowired
    private IPesquisaRepository repository;
    private List<PesquisaSubject> subjects = new ArrayList<>();

    private List<EmpresaSubject> empresaSubjects = new ArrayList<>();

    @Autowired
    private ISeguidoresRepository seguidorRepository;
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

    @Autowired
    private ICupomRepository cupomRepository;
    public Pesquisa salvar(Pesquisa pesquisa){
        Integer idEmpresa = pesquisa.getEmpresa().getId();
        List<Integer> empresasAtivas = new ArrayList<>();
        empresaSubjects.forEach((empresa) -> {
                empresasAtivas.add(empresa.getEmpresa().getId());
            }
        );
        if(!empresasAtivas.contains(idEmpresa)){
            Optional<Empresa> empresaNova = empresaRepository.findById(idEmpresa);
            empresaSubjects.add(new EmpresaSubject(empresaNova));
        }
        empresaSubjects.forEach((empresaSubject) -> {
                if (empresaSubject.getIdEmpresa() == idEmpresa && !pesquisa.isInterna()){
                    empresaSubject.novaPesquisa(pesquisa);
                }
            }
        );
        if(pesquisa.getId()==null){
            registrarPesquisa(pesquisa);
        }
        pesquisaRepository.save(pesquisa);
        return pesquisa;
    }
    public void registrarPesquisa(Pesquisa pesquisa){
        if (subjects.size() == 0){
            List<Optional<Pesquisa>> pesquisasAtivas = pesquisaRepository.findAllByEncerradaFalse();
            pesquisasAtivas.forEach((pesquisaAtiva) -> {subjects.add(new PesquisaSubject(pesquisaAtiva.get()));});
        }
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

    public Cupom responderPesquisa(Gabarito gabarito){
        Cupom cupom = new Cupom();
        if (gabarito.getConvidado() == null){
            if (gabarito.getRespondente() == null){
                return cupom;
            }
        }
//        if (getSubjectPorPesquisa(gabarito.getPesquisa()) == null){
//            return cupom;
//        }
        if (gabarito.getConvidado() != null && gabarito.getRespondente() == null
            && getSubjectPorPesquisa(gabarito.getPesquisa()) != null){
            convidadoRepository.save(gabarito.getConvidado());
            if (!pesquisaRepository.existsById(gabarito.getPesquisa().getId())){
                return cupom;
            }
            gabaritoRepository.save(gabarito);
            PesquisaSubject pesquisaRespondida = getSubjectPorPesquisa(gabarito.getPesquisa());
            if(pesquisaRespondida.adicionarRespostaEncerrar()){
                encerrarPesquisa(gabarito.getPesquisa());
            }
            return null;
        }
        if (gabarito.getConvidado() == null && gabarito.getRespondente() != null){
            if (!pesquisaRepository.existsById(gabarito.getPesquisa().getId())){
                return cupom;
            }
            if (pesquisaRepository.findById(gabarito.getPesquisa().getId()).get().isInterna()){
                return cupom;
            }
            if (pesquisaRepository.findById(gabarito.getPesquisa().getId()).get().getEncerrada()){
                return cupom;
            }
            if (!seguidorRepository.existsByRespondenteIdAndEmpresaId(gabarito.getRespondente().getId(),
                    pesquisaRepository.findById(gabarito.getPesquisa().getId()).get().getEmpresa().getId())){
                return cupom;
            }
            gabaritoRepository.save(gabarito);
            //PesquisaSubject pesquisaRespondida = getSubjectPorPesquisa(gabarito.getPesquisa());
            if(pesquisaRepository.findById(gabarito.getPesquisa().getId()).get().getParticipantesAlvo() <= gabaritoRepository.countGabaritoIdByPesquisaId(gabarito.getPesquisa().getId()).intValue()){
                encerrarPesquisa(gabarito.getPesquisa());
            }
            cupom.setAtivo(true);
            cupom.setValidade(gabarito.getCriadoEm().plusMonths(3));
            cupom.setPercentual(15.0);
            cupom.setGabarito(gabarito);
            cupomRepository.save(cupom);
            return cupom;
        }
        //if (gabarito.getRespondente() != null)
        return cupom;
    }
    public void encerrarPesquisa(Pesquisa pesquisa){
        Optional<Pesquisa> pesquisaRespondida = pesquisaRepository.findById(pesquisa.getId());
        pesquisaRespondida.get().setEncerrada(true);
        pesquisaRepository.save(pesquisaRespondida.get());
//        pesquisaRespondida.removerTodosEmpresa();
//        pesquisaRespondida.removerTodos();
//        subjects.remove(pesquisaRespondida);
//        empresaSubjects.forEach((empresa) ->{
//                if(empresa.getIdEmpresa() == pesquisa.getEmpresa().getId()){
//                    empresa.encerrarPesquisa(pesquisa);
//                }
//            }
//        );
    }

    public List<Pesquisa> pesquisaPorRespondente(Integer idRespondente){
        List<EmpresaSubject> empresas = new ArrayList<>();
        for (EmpresaSubject empresa : empresaSubjects) {
            List<RespondenteObserver> respondentes = empresa.getRespondentes();
            for (int i = 0; i < respondentes.size(); i++) {
                if (respondentes.get(i).getIdUsuario().intValue() == idRespondente) {
                    return respondentes.get(i).getPesquisasDisponiveis();
                }
            }
        }
        return null;
    }
    public Seguidores seguirEmpresa(Respondente respondente, Integer idEmpresa){
        Seguidores novoSeguidor = new Seguidores();
        for (EmpresaSubject empresa : empresaSubjects) {
            if (empresa.getIdEmpresa() == idEmpresa) {
                if (seguidorRepository
                        .findAllByRespondenteIdAndEmpresaId(
                                respondente.getId().longValue(),idEmpresa.intValue()).isEmpty())
                {
                    empresa.adicionarRespondente(new RespondenteObserver(respondente));
                    novoSeguidor.setRespondente(respondente);
                    Empresa empresa1 = new Empresa();
                    empresa1.setId(idEmpresa);
                    novoSeguidor.setEmpresa(empresa1);
                    return seguidorRepository.save(novoSeguidor);
                }
                else{
                    seguidorRepository.deleteByRespondenteIdAndEmpresaId(
                            respondente.getId().longValue(),idEmpresa.intValue());
                    empresa.removerRespondente(new RespondenteObserver(respondente));
                    return novoSeguidor;
                }
            }
        }
        return null;
    }
}
