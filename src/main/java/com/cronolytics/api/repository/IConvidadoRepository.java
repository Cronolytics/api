package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Convidado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConvidadoRepository extends JpaRepository<Convidado, Integer> {
}
