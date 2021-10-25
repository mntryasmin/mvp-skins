package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name="tb_categoria_preco")
@Data
public class CategoriaPreco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CODIGO_CATEGORIA_PRECO")
    private Long id;

    @Column(name="DESCRICAO", nullable = false)
    private String descricao;
}
