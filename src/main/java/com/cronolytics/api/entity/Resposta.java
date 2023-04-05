package com.cronolytics.api.entity;

import com.cronolytics.api.dto.res.RespostaSimplesDTO;

import javax.persistence.*;

@NamedNativeQuery(name = "Resposta.RespostaSimplesDTOByIdPergunta",
        query = "SELECT re.id AS id, " +
                "re.descri AS label, " +
                "(SELECT COUNT(*) FROM resposta_gabarito re_gab WHERE re_gab.resposta_id = re.id) AS qtd_respostas " +
                "FROM resposta re WHERE re.id_pergunta = :idPergunta",
        resultSetMapping = "com.cronolytics.api.dto.res.RespostaSimplesDTO"
)
@SqlResultSetMapping(
        name = "com.cronolytics.api.dto.res.RespostaSimplesDTO",
        classes = @ConstructorResult(
                targetClass = RespostaSimplesDTO.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "label"),
                        @ColumnResult(name = "qtd_respostas")
                        //@ColumnResult(name = "pct_total")
                }
        )
)
@Entity
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descri;

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
}
