package com.cronolytics.api.repository;

import com.cronolytics.api.dto.res.RespostaSimplesDTO;
import com.cronolytics.api.entity.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IRespostasRepository extends JpaRepository<Resposta, Integer> {

    @Query(nativeQuery = true)
    List<Optional<RespostaSimplesDTO>> RespostaSimplesDTOByIdPergunta(int idPergunta);
}
