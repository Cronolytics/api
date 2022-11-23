package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Pesquisa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPesquisaRepository extends JpaRepository<Pesquisa,Integer> {
    List<Optional<Pesquisa>> findAllByEmpresaId(int id);
}
