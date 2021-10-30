package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TB_TIPO_NF")
public class TipoNF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_TIPO_NF")
    private Long id;

    @Column(nullable = false, name = "DESCRICAO")
    private String descricao;
}
