package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICupomRepository extends JpaRepository<Cupom,Long> {
}
