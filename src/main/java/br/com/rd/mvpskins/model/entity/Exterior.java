package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name="tb_exterior")
@Data
public class Exterior {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CODIGO_EXTERIOR")
    private Long id;

    @Column(name="DESCRICAO", nullable = false)
    private String descricao;
}
