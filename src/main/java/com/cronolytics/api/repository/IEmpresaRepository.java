package com.cronolytics.api.repository;

import com.cronolytics.api.dto.res.PerguntaSimplesDTO;
import com.cronolytics.api.dto.res.SeguidoresDTO;
import com.cronolytics.api.entity.Empresa;
import com.cronolytics.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {
    Empresa findByEmail(String email);
    Empresa findByEmailAndSenha(String email, String senha);

    boolean existsByEmailAndCnpj(String email, String cnpj);


}
