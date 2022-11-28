package com.cronolytics.api.entity;

import com.cronolytics.api.dto.res.PerguntaSimplesDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedNativeQuery(name = "Pergunta.PerguntaSimplesDTOByIdPesquisa",
            query = "SELECT " +
                    "pe.id AS id_perg, pe.descri AS titulo, " +
                    "comp.multi_esc AS multi, " +
                    "comp.nome AS nome_comp, " +
                    "comp.id AS id_comp " +
                    "FROM pergunta pe " +
                    "JOIN componente comp ON pe.componente_id = comp.id " +
                    "WHERE pe.id_pesquisa= :idPesquisa",
        resultSetMapping = "com.cronolytics.api.dto.res.PerguntaSimplesDTO"
)
@SqlResultSetMapping(
        name = "com.cronolytics.api.dto.res.PerguntaSimplesDTO",
        classes = @ConstructorResult(
                targetClass = PerguntaSimplesDTO.class,
                columns = {
                        @ColumnResult(name = "id_perg"),
                        @ColumnResult(name = "titulo"),
                        @ColumnResult(name = "multi"),
                        @ColumnResult(name = "nome_comp"),
                        @ColumnResult(name = "id_comp")
                }
        )
)
@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descri;
    @ManyToOne
    private Componente componente;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "id_pergunta")
    private List<Resposta> respostas = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return descri;
    }

    public void setDesc(String desc) {
        this.descri = desc;
    }

    public Componente getComponente() {
        return componente;
    }

    public void setComponente(Componente componente) {
        this.componente = componente;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }
}
