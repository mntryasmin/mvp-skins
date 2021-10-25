package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name="tb_colecao")
@Data
public class Colecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CODIGO_COLECAO")
    private Long id;

    @Column(name="DESCRICAO", nullable = false)
    private String descricao;
}
