package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Respondente;
import com.cronolytics.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRespondenteRepository extends JpaRepository<Respondente, Long> {
    Optional<Respondente> findById(Long id);
    Optional<Respondente> findByEmailAndSenha(String email, String senha);
    boolean existsByEmailOrCpf(String email, String cpf);
}
