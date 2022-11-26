package com.cronolytics.api.repository;

import com.cronolytics.api.dto.res.PerguntaSimplesDTO;
import com.cronolytics.api.entity.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPerguntaRepository extends JpaRepository<Pergunta, Integer> {
    @Query(nativeQuery = true)
    List<Optional<PerguntaSimplesDTO>> PerguntaSimplesDTOByIdPesquisa(int idPesquisa);
}
