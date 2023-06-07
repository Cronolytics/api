package com.cronolytics.api.entity;

import com.cronolytics.api.dto.res.PesquisaDisponivelSimplesDTO;
import com.cronolytics.api.dto.res.PesquisaMobileDetalhesDTO;
import com.cronolytics.api.dto.res.PesquisaSimplesDTO;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamedNativeQuery(name = "Pesquisa.PesquisaSimplesDTOByIdEmpresa",
        query = "SELECT " +
                "p.id AS id, " +
                "p.nome AS titulo, " +
                "(SELECT COUNT(pe.descri) FROM pergunta pe WHERE pe.id_pesquisa = p.id) AS perguntas, " +
                "p.encerrada AS em_andamento, p.interna AS interna, " +
                "p.exploratoria AS exploratoria " +
                "FROM pesquisa p " +
                "WHERE p.empresa_id = :idEmpresa",
        resultSetMapping = "com.cronolytics.api.dto.res.PesquisaSimplesDTO")
@SqlResultSetMapping(
        name = "com.cronolytics.api.dto.res.PesquisaSimplesDTO",
        classes = @ConstructorResult(
                targetClass = PesquisaSimplesDTO.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "titulo"),
                        @ColumnResult(name = "perguntas"),
                        @ColumnResult(name = "em_andamento"),
                        @ColumnResult(name = "interna"),
                        @ColumnResult(name = "exploratoria")
                }
        )
)

@NamedNativeQuery(name = "Pesquisa.PesquisaMobileDetalhesDTOByIdEmpresa",
        query = "SELECT nome AS nome, " +
                "criada_em AS criada, " +
                "(SELECT COUNT(descri) " +
                    "FROM pergunta pe " +
                        "WHERE id_pesquisa = p.id) " +
                "AS qtd_perguntas, encerrada FROM pesquisa p WHERE empresa_id = :idEmpresa AND interna = 0",
        resultSetMapping = "com.cronolytics.api.dto.res.PesquisaMobileDetalhesDTO")
@SqlResultSetMapping(
        name = "com.cronolytics.api.dto.res.PesquisaMobileDetalhesDTO",
        classes = @ConstructorResult(
                targetClass = PesquisaMobileDetalhesDTO.class,
                columns = {
                        @ColumnResult(name = "nome"),
                        @ColumnResult(name = "criada"),
                        @ColumnResult(name = "qtd_Perguntas"),
                        @ColumnResult(name = "encerrada")
                }
        )
)

@NamedNativeQuery(name = "Pesquisa.PesquisaDisponivelSimplesDTOByIdEmpresa",
        query = "SELECT e.nome AS nome_empresa," +
                " p.nome AS pes_nome," +
                " p.participantes_alvo AS limite_de_respostas," +
                "(SELECT COUNT(g.pesquisa_id) " +
                    "FROM gabarito g " +
                    "WHERE g.pesquisa_id = p.id) AS qtd_respostas " +
                "FROM empresa e " +
                "JOIN pesquisa p ON p.empresa_id = e.id " +
                "WHERE e.id in (:idsEmpresas) " +
                "AND p.encerrada = 0 " +
                "AND p.interna = 0 " +
                "ORDER BY p.criada_em DESC",
        resultSetMapping = "com.cronolytics.api.dto.res.PesquisaDisponivelSimplesDTO")
@SqlResultSetMapping(
        name = "com.cronolytics.api.dto.res.PesquisaDisponivelSimplesDTO",
        classes = @ConstructorResult(
                targetClass = PesquisaDisponivelSimplesDTO.class,
                columns = {
                        @ColumnResult(name = "nome_empresa"),
                        @ColumnResult(name = "pes_nome"),
                        @ColumnResult(name = "limite_de_respostas"),
                        @ColumnResult(name = "qtd_respostas")
                }
        )
)

@Entity
public class Pesquisa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descri;
    private boolean exploratoria;
    private boolean encerrada;
    private Integer participantesAlvo;
    private boolean interna;
    @ManyToOne
    private Empresa empresa;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "id_pesquisa")
    private List<Pergunta> perguntas = new ArrayList<>();
    @Nullable
    private Date criadaEm;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return descri;
    }

    public void setDesc(String desc) {
        this.descri = desc;
    }

    public boolean getExploratoria() {
        return exploratoria;
    }

    public void setExploratoria(boolean exploratoria) {
        this.exploratoria = exploratoria;
    }

    public boolean getEncerrada() {
        return encerrada;
    }


    public void setEncerrada(boolean encerrada) {
        this.encerrada = encerrada;
    }

    public Integer getParticipantesAlvo() {
        return participantesAlvo;
    }

    public void setParticipantesAlvo(Integer participantesAlvo) {
        this.participantesAlvo = participantesAlvo;
    }
    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(List<Pergunta> perguntas) {
        this.perguntas = perguntas;
    }

    public boolean isExploratoria() {
        return exploratoria;
    }

    public boolean isEncerrada() {
        return encerrada;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean isInterna() {
        return interna;
    }

    public void setInterna(boolean interna) {
        this.interna = interna;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public Date getCriadaEm() {
        return criadaEm;
    }

    public void setCriadaEm(Date criadaEm) {
        this.criadaEm = criadaEm;
    }
}
