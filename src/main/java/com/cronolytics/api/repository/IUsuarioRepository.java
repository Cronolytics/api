package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    Usuario findByEmailAndSenha(String email, String senha);

    boolean existsByEmailAndCpf(String email, String cpf);
}
