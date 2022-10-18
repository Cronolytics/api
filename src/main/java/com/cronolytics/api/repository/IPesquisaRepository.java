package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Pesquisa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPesquisaRepository extends JpaRepository<Pesquisa,Integer> {
}
