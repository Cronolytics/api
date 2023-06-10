package com.cronolytics.api.entity;

import com.cronolytics.api.dto.res.CupomDTO;
import com.cronolytics.api.dto.res.PesquisaMobileDetalhesDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

//@NamedNativeQuery(name = "Cupom.findByRespondenteId",
//        query = "SELECT " +
//                "cupom.id" +
//                //" cupom.ativo," +
//                //" cupom.codigo," +
//                //" cupom.percentual," +
//                //" cupom.validade," +
//                //" cupom.valor" +
//                " FROM cupom" +
//                " JOIN gabarito" +
//                " ON gabarito.id = cupom.gabarito_id" +
//                " WHERE gabarito.respondente_id = :idRespondente",
//        resultSetMapping = "com.cronolytics.api.dto.res.CupomDTO")
//@SqlResultSetMapping(
//        name = "com.cronolytics.api.dto.res.CupomDTO",
//        classes = @ConstructorResult(
//                targetClass = CupomDTO.class,
//                columns = {
//                        @ColumnResult(name = "id")
//                        //@ColumnResult(name = "ativo"),
//                        //@ColumnResult(name = "codigo"),
//                        //@ColumnResult(name = "percentual"),
//                        //@ColumnResult(name = "validade"),
//                        //@ColumnResult(name = "valor")
//                }
//        )
//)
@Entity
public class Cupom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private LocalDate validade;
    @Nullable
    private Double valor;
    @Nullable
    private Double percentual;
    private Boolean ativo;

    @OneToOne
    private Gabarito gabarito;

    private String nomeEmpresa;

    public Cupom() {
        this.codigo = UUID.randomUUID().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo.toString();
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Nullable
    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(@Nullable Double percentual) {
        this.percentual = percentual;
    }

    public Integer getGabarito() {
        return gabarito.getId();
    }

    public void setGabarito(Gabarito gabarito) {
        this.gabarito = gabarito;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }
}
