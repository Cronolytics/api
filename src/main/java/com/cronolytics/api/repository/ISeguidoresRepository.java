package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Seguidores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ISeguidoresRepository extends JpaRepository<Seguidores,Integer> {
    List<Optional<Seguidores>> findAllByRespondenteIdAndEmpresaId (Long idRespondente, int idEmpresa);
    @Modifying
    @Transactional
    @Query(value = "delete from seguidores  where respondente_id=:idRespondente AND empresa_id=:idEmpresa",nativeQuery = true)
    void deleteByRespondenteIdAndEmpresaId (Long idRespondente,Integer idEmpresa);
    boolean existsByRespondenteIdAndEmpresaId (Long idRespondente, Integer idEmpresa);
    Optional<List<Seguidores>> findByRespondenteId(Long idRespondente);
}
