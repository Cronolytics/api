package com.cronolytics.api.repository;

import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {
    Empresa findByEmail(String email);
    Empresa findByEmailAndSenha(String email, String senha);

    boolean existsByEmailAndCnpj(String email, String cnpj);
}
