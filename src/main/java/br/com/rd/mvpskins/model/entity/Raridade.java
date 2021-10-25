package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name="tb_raridade")
@Data
public class Raridade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CODIGO_RARIDADE")
    private Long id;

    @Column(name="DESCRICAO", nullable = false)
    private String descricao;
}
