package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "TB_NF")
public class NF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_nf")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_pedido")
    private Request request;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_tipo_nf")
    private TypeNF typeNF;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "codigo_empresa")
    @Column(nullable = false, name = "codigo_empresa")
    private Long idCompany;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "codigo_fornecedor")
    @Column(nullable = false, name = "codigo_fornecedor")
    private Long idProvider;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "codigo_client")
    @Column(nullable = false, name = "codigo_client")
    private Long idClient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_forma_pagamento")
    private FormPayment formPayment;

    @Column(nullable = false, name = "chave_de_acesso")
    private String accessKey;

    @Column(nullable = false, name = "numero_nota")
    private String noteNumber;

    @Column(nullable = false)
    private Double icms;

    @Column(nullable = false)
    private Double ipi;

    @Column(nullable = false)
    private Double pis;

    @Column(nullable = false)
    private Double cofins;

    @Column(nullable = false, name = "flag_nota_paulista")
    private Boolean flagNF;

    @Column (name = "data_emissao")
    private Date issueDate;

    @Column(nullable = false, name = "desconto_produto")
    private Double discountProduct;

    @Column(nullable = false, name = "valor_total_bruto")
    private Double grossAddedValue;

    @Column(nullable = false, name = "valor_total_liquido")
    private Double netValue;
}