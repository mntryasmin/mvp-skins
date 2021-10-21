package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "tb_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long nf;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double icms;

    @Column(nullable = false)
    private Double pis;

    @Column(nullable = false)
    private Double cofins;

    @Column(nullable = false)
    private Double ipi;

    @Column(nullable = false)
    private Double desconto;

    @Column(nullable = false)
    private Double valor_bruto;

    @Column(nullable = false)
    private Double valor_liquido;
}