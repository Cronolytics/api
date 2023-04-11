package com.cronolytics.api.repository;

import com.cronolytics.api.dto.res.PesquisaSimplesDTO;
import com.cronolytics.api.entity.Pesquisa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPesquisaRepository extends JpaRepository<Pesquisa,Integer> {
    List<Optional<Pesquisa>> findAllByEmpresaId(int id);
    @Query(nativeQuery = true)
    List<Optional<PesquisaSimplesDTO>> PesquisaSimplesDTOByIdEmpresa(int idEmpresa);
    List<Optional<Pesquisa>> findAllByEncerradaFalse();
    List<Optional<Pesquisa>> findAllByEncerradaFalseAndInternaFalse();
}
