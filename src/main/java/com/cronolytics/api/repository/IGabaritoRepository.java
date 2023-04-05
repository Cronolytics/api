package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Gabarito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface IGabaritoRepository extends JpaRepository<Gabarito,Integer> {
    BigInteger countGabaritoIdByPesquisaId(int id);
}
