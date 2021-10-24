package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TB_FORMA_PAGAMENTO")
public class FormPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_forma_pagamento")
    private Long id;

    @Column(nullable = false, name = "descricao")
    private String description;
}
