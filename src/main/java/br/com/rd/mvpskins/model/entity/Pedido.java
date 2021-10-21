package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn
    @Column(nullable = false)
    private Integer codigo_empresa;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn
    @Column(nullable = false)
    private Integer codigo_cliente;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn
    @Column(nullable = false)
    private Integer codigo_forma_pagamento;

    @Column
    private Date data_emissao;

    @Column(nullable = false)
    private Double desconto_produto;

    @Column(nullable = false)
    private Double valor_bruto;

    @Column(nullable = false)
    private Double valor_liquido;
}