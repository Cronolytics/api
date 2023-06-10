package com.cronolytics.api.repository;

import com.cronolytics.api.dto.res.EmpresaSimplesDTO;
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

    @Query(nativeQuery = true)
    List<Optional<EmpresaSimplesDTO>> EmpresaSimplesDTOByIdRespondente(int idRespondente);
    @Query(value = "SELECT nome FROM empresa WHERE id = :idEmpresa", nativeQuery = true)
    Optional<String> findNomeById(Integer idEmpresa);

    @Query(value = "SELECT e.nome FROM gabarito g JOIN pesquisa p ON g.pesquisa_id = p.id JOIN empresa e ON p.empresa_id = e.id WHERE g.id = :idGabarito", nativeQuery = true)
    Optional<String> findNomeByGabaritoId(Integer idGabarito);
}
