package com.cronolytics.api.dto.res;

import com.cronolytics.api.repository.IGabaritoRepository;
import com.cronolytics.api.repository.IPerguntaRepository;
import com.cronolytics.api.repository.IPesquisaRepository;
import com.cronolytics.api.repository.IRespostasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

public class MediaPesquisaDTO {
    private int id;
    private List<Optional<PerguntaSimplesDTO>> perguntas;
    private int respostasTotais;

    public MediaPesquisaDTO(Integer id, List<Optional<PerguntaSimplesDTO>> perguntas, Integer respostasTotais) {
        this.id = id.intValue();
        this.perguntas = perguntas;
        this.respostasTotais = respostasTotais.intValue();
    }

    public MediaPesquisaDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Optional<PerguntaSimplesDTO>> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Optional<PerguntaSimplesDTO>> perguntas) {
        this.perguntas = perguntas;
    }

    public int getRespostasTotais() {
        return respostasTotais;
    }

    public void setRespostasTotais(int respostasTotais) {
        this.respostasTotais = respostasTotais;
    }
}
