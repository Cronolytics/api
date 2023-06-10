package com.cronolytics.api.repository;

import com.cronolytics.api.dto.res.CupomDTO;
import com.cronolytics.api.entity.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

public interface ICupomRepository extends JpaRepository<Cupom,Long> {
    @Query(value = "SELECT cupom.* FROM cupom JOIN gabarito ON gabarito.id = cupom.gabarito_id WHERE gabarito.respondente_id = :idRespondente",nativeQuery = true)
    List<Optional<Cupom>> cupomPorIdRespondente(Long idRespondente);
    @Modifying
    @Transactional
    @Query(value = "UPDATE cupom SET ativo = 0 WHERE id = :idCupom", nativeQuery = true)
    void updateCupomById(Long idCupom);
}
