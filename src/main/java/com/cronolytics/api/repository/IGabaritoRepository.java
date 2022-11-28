package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Gabarito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGabaritoRepository extends JpaRepository<Gabarito,Integer> {
    int countByPesquisaId(int id);
}
