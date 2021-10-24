package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "TB_PEDIDO")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_pedido")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_empresa")
    private Empresa company;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_cliente")
    private Cliente client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_forma_pagamento")
    private FormPayment formPayment;

    @Column (name = "data_emissao")
    private Date issueDate;

    @Column(nullable = false, name = "desconto_produto")
    private Double discountProduct;

    @Column(nullable = false, name = "valor_total_bruto")
    private Double grossAddedValue;

    @Column(nullable = false, name = "valor_total_liquido")
    private Double netValue;
}