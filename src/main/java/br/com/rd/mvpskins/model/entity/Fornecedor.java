package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TB_FORNECEDOR")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_FORNECEDOR")
    private Long id;

    @Column(name = "CNPJ")
    private String cnpj;

    @Column(nullable = false, name = "DESCRICAO")
    private String descricao;
}
