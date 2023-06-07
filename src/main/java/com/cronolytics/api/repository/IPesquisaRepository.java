package com.cronolytics.api.repository;

import com.cronolytics.api.dto.res.PesquisaDisponivelSimplesDTO;
import com.cronolytics.api.dto.res.PesquisaMobileDetalhesDTO;
import com.cronolytics.api.dto.res.PesquisaSimplesDTO;
import com.cronolytics.api.entity.Pesquisa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IPesquisaRepository extends JpaRepository<Pesquisa,Integer> {
    List<Optional<Pesquisa>> findAllByEmpresaId(int id);
    @Query(nativeQuery = true)
    List<Optional<PesquisaSimplesDTO>> PesquisaSimplesDTOByIdEmpresa(Integer idEmpresa);
    @Query(nativeQuery = true)
    List<Optional<PesquisaMobileDetalhesDTO>> PesquisaMobileDetalhesDTOByIdEmpresa(int idEmpresa);

    @Query(nativeQuery = true)
    List<Optional<PesquisaDisponivelSimplesDTO>> PesquisaDisponivelSimplesDTOByIdEmpresa(List<Integer> idsEmpresas);
    List<Optional<Pesquisa>> findAllByEncerradaFalse();
    @Query(value = "SELECT e.id FROM pesquisa p JOIN empresa e ON p.empresa_id = e.id WHERE p.id = :idPesquisa", nativeQuery = true)
    Integer empresaByIdPesquisa(Integer idPesquisa);
    List<Optional<Pesquisa>> findAllByEncerradaFalseAndInternaFalse();
    List<Optional<Pesquisa>> findByEmpresaIdAndEncerradaFalse(int idEmpresa);
}
