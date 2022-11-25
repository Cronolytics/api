package com.cronolytics.api.entity;

import com.cronolytics.api.dto.res.PesquisaSimplesDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(name = "Pesquisa.PesquisaSimplesDTOByIdEmpresa",
        query = "SELECT " +
                "p.id AS id, " +
                "p.nome AS titulo, " +
                "(SELECT COUNT(pe.id) FROM pergunta pe WHERE pe.id_pesquisa = p.id) AS perguntas, " +
                "(SELECT COUNT(gab.id) FROM gabarito gab WHERE gab.pesquisa_id=p.id) AS pessoas, " +
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
                        @ColumnResult(name = "pessoas"),
                        @ColumnResult(name = "em_andamento"),
                        @ColumnResult(name = "interna"),
                        @ColumnResult(name = "exploratoria")
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
}
