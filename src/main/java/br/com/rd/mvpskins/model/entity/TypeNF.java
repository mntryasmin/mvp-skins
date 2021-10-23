package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TB_TIPO_NF")
public class TypeNF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_tipo_nf")
    private Long id;

    @Column(nullable = false, name = "descricao")
    private String description;
}
