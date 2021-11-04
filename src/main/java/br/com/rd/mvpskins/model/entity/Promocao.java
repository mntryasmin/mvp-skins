package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "tb_promocao")

public class Promocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_PROMOCAO",nullable = false)
    private String codigoPromocao;
    @Column(name = "DESCRICAO",nullable = false)
    private String descricao;


}
