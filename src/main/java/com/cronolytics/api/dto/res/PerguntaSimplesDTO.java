package com.cronolytics.api.dto.res;

import com.cronolytics.api.entity.Componente;
import com.cronolytics.api.repository.IRespostasRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PerguntaSimplesDTO {
    private int id;
    private String titulo;
    private Componente componente;
    private  List<Optional<RespostaSimplesDTO>> respostas;

    public PerguntaSimplesDTO(int id, String titulo, boolean multi_esc,String nomeComp, int idComp) {
        this.id = id;
        this.titulo = titulo;
        this.componente = new Componente(idComp,nomeComp,multi_esc);
    }

    public PerguntaSimplesDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public List<Optional<RespostaSimplesDTO>> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Optional<RespostaSimplesDTO>> respostas) {
        this.respostas = respostas;
    }
}
