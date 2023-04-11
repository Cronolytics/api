package com.cronolytics.api.repository;

import com.cronolytics.api.dto.res.SeguidoresDTO;
import com.cronolytics.api.entity.Respondente;
import com.cronolytics.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IRespondenteRepository extends JpaRepository<Respondente, Long> {
    Optional<Respondente> findById(Long id);
    Optional<Respondente> findByEmailAndSenha(String email, String senha);
    boolean existsByEmailOrCpf(String email, String cpf);
}
