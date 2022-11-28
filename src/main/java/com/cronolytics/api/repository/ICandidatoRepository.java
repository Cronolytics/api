package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICandidatoRepository extends JpaRepository<Candidato, Integer> {
}
