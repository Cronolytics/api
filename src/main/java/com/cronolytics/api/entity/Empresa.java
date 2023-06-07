package com.cronolytics.api.entity;

import com.cronolytics.api.dto.res.EmpresaSimplesDTO;
import com.cronolytics.api.dto.res.PerguntaSimplesDTO;
import com.cronolytics.api.dto.res.PesquisaDisponivelSimplesDTO;
import com.cronolytics.api.dto.res.SeguidoresDTO;
import com.cronolytics.api.utils.enums.StatusAccount;

import javax.persistence.*;
import java.time.LocalDate;
@NamedNativeQuery(name = "Empresa.EmpresaSimplesDTOByIdRespondente",
        query = "SELECT e.nome AS nome_empresa," +
                "(SELECT COUNT(p.nome) " +
                    "FROM pesquisa p " +
                    "WHERE p.empresa_id = e.id AND interna = 0) AS qtd_pesq, " +
                "(SELECT id " +
                    "FROM seguidores " +
                    "WHERE empresa_id = e.id AND respondente_id = :idRespondente) is null AS inscrito " +
                "FROM empresa e",
        resultSetMapping = "com.cronolytics.api.dto.res.EmpresaSimplesDTO")
@SqlResultSetMapping(
        name = "com.cronolytics.api.dto.res.EmpresaSimplesDTO",
        classes = @ConstructorResult(
                targetClass = EmpresaSimplesDTO.class,
                columns = {
                        @ColumnResult(name = "nome_empresa"),
                        @ColumnResult(name = "qtd_pesq"),
                        @ColumnResult(name = "inscrito")
                }
        )
)
@Entity
public class Empresa extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cnpj;
    @OneToOne(cascade = CascadeType.PERSIST)
    private ContaBancaria contaBancaria;
    private StatusAccount statusConta;

    public Empresa(String img, String nome, String senha, String email, String cep, String telefone) {
        super(img, nome, senha, email, cep, telefone);
    }

    public Empresa(String img, String nome, String senha, String email, String cep, String telefone, Integer id, String cnpj, ContaBancaria contaBancaria) {
        super(img, nome, senha, email, cep, telefone);
        this.id = id;
        this.cnpj = cnpj;
        this.contaBancaria = contaBancaria;
        this.statusConta = StatusAccount.PENDING;
    }

    public Empresa() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public String getSenha(){
        return super.getSenha();
    }

    public void setSenha(String senha){
        super.setSenha(senha);
    }

    public String getEmail(){
        return super.getEmail();
    }

    public void setEmail(String email){
        super.setEmail(email);
    }

    public StatusAccount getStatus() {
        return statusConta;
    }
    public void setStatus(StatusAccount status) {
        this.statusConta = status;
    }
}