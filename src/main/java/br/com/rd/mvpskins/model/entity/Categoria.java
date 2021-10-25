package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "tb_categoria_produto")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CODIGO_CATEGORIA")
    private Long codigoCategoria;

    @Column(name="DESCRICAO", nullable = false)
    private String descricao;

}
