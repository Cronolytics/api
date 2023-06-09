package com.cronolytics.api.controller;

import com.cronolytics.api.dto.res.MediaPesquisaDTO;
import com.cronolytics.api.dto.res.PerguntaSimplesDTO;
import com.cronolytics.api.dto.res.PesquisaSimplesDTO;
import com.cronolytics.api.entity.Cupom;
import com.cronolytics.api.entity.Gabarito;
import com.cronolytics.api.entity.Pesquisa;
import com.cronolytics.api.repository.*;
import com.cronolytics.api.service.PesquisaService;
import com.cronolytics.api.utils.PilhaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pesquisas")
public class PesquisaController {
    @Autowired
    IEmpresaRepository empresaRepository;
    @Autowired
    IPesquisaRepository pesquisaRepository;
    @Autowired
    PesquisaService service;
    @Autowired
    IRespondenteRepository respondenteRepository;

    @Autowired
    private IGabaritoRepository gabaritoRepository;
    @Autowired
    private IPerguntaRepository perguntaRepository;
    @Autowired
    private IRespostasRepository respostasRepository;

    @Autowired
    private ISeguidoresRepository seguidoresRepository;
    @PostMapping("/gravar")
    public ResponseEntity<Pesquisa> salvarPesquisa(@RequestBody Pesquisa pesquisa){
        service.salvar(pesquisa);
        return ResponseEntity.status(201).body(pesquisa);
    }

    @PostMapping("/responder")
    public ResponseEntity responder(@RequestBody Gabarito gabarito){
        if(gabaritoRepository.existsByPesquisaIdAndRespondenteId(gabarito.getPesquisa().getId(),gabarito.getRespondente().getId().longValue())||!seguidoresRepository.existsByRespondenteIdAndEmpresaId(gabarito.getRespondente().getId().longValue(), pesquisaRepository.empresaByIdPesquisa(gabarito.getPesquisa().getId()))){
            return ResponseEntity.status(207).build();
        }
        Cupom cupom = service.responderPesquisa(gabarito);
        return cupom == null ?
                ResponseEntity.status(201).body(gabarito) :
                ResponseEntity.status(201).body(cupom);
    }

    @GetMapping
    public ResponseEntity getPesquisa(@RequestParam(required = true) Integer idPesquisa){
        if(!pesquisaRepository.existsById(idPesquisa)){
            return ResponseEntity.status(404).build();
        }
        Optional<Pesquisa> pesquisa = pesquisaRepository.findById(idPesquisa);
        return ResponseEntity.status(200).body(pesquisa);
    }

    @GetMapping("/pesquisas-simples")
    public ResponseEntity pesquisasSimples(@RequestParam(required = true) Integer idEmpresa){
        if(!empresaRepository.existsById(idEmpresa)){
            return ResponseEntity.status(404).build();
        }
        List<Optional<PesquisaSimplesDTO>> pesquisas = pesquisaRepository.PesquisaSimplesDTOByIdEmpresa(idEmpresa);
        pesquisas.forEach((pesquisa)->{int qtdRespostas = gabaritoRepository.countGabaritoIdByPesquisaId(pesquisa.get().getId()).intValue(); pesquisa.get().setQtdPessoas( qtdRespostas != 0 ? qtdRespostas : 0);});

        PilhaObj<Optional<PesquisaSimplesDTO>> pilha = new PilhaObj<>(pesquisas.size());

        List<PesquisaSimplesDTO> pesquisaArrayFinal = new ArrayList<>();

        if (pesquisas.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        for (int i = 0; i < pesquisas.size(); i++) {
            pilha.push(pesquisas.get(i));
        }

        for (int i = 0; i < pilha.getTamanho(); i++) {
            pesquisaArrayFinal.add(pilha.pop().get());
        }

        return ResponseEntity.status(200).body(pesquisaArrayFinal);
    }

    @GetMapping("/media-pesquisa")
    public ResponseEntity mediaPesquisa(@RequestParam(required = true) Integer idPesquisa){
        if(!pesquisaRepository.existsById(idPesquisa)){
            return ResponseEntity.status(404).build();
        }
        List<Optional<PerguntaSimplesDTO>> perguntas = perguntaRepository.PerguntaSimplesDTOByIdPesquisa(idPesquisa);
        perguntas.forEach((pergunta)->{pergunta.get().setRespostas(respostasRepository.RespostaSimplesDTOByIdPergunta(pergunta.get().getId()));});
        BigInteger respostasTotais = gabaritoRepository.countGabaritoIdByPesquisaId(idPesquisa);
        if(respostasTotais.intValue() != 0){
            perguntas.forEach((pergunta)->{pergunta.get().getRespostas().forEach((resposta)->{resposta.get().setPctTotal(resposta.get().getQtdRespostas() == 0 ? 0.0 : resposta.get().getQtdRespostas() * 100/ respostasTotais.intValue());});});
        }
        MediaPesquisaDTO media = new MediaPesquisaDTO(idPesquisa,perguntas,respostasTotais.intValue());
        return ResponseEntity.status(200).body(media);
    }
}
