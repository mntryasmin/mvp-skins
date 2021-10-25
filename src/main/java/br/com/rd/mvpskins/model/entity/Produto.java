package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name="tb_produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_PRODUTO")
    private Long id;

    @Column(name="DESCRICAO", nullable = false)
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CODIGO_CATEGORIA",
            insertable = false,
            updatable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name="CODIGO_SUBCATEGORIA",
                    referencedColumnName = "CODIGO_SUBCATEGORIA",
                    insertable = false,
                    updatable = false),
            @JoinColumn(name="CODIGO_CATEGORIA",
                    referencedColumnName = "CODIGO_CATEGORIA",
                    insertable = false,
                    updatable = false)
    })
    private Subcategoria subcategoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CODIGO_COLECAO",
            referencedColumnName = "CODIGO_COLECAO",
            insertable = false,
            updatable = false)
    private Colecao colecao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CODIGO_RARIDADE",
            referencedColumnName = "CODIGO_RARIDADE",
            insertable = false,
            updatable = false)
    private Raridade raridade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CODIGO_EXTERIOR",
            referencedColumnName = "CODIGO_EXTERIOR",
            insertable = false,
            updatable = false)
    private Exterior exterior;

    @Column(name="FLOAT_DESGASTE", nullable = false)
    private double desgaste;

    @Column(name="Pattern", nullable = false)
    private Integer pattern;

    @Column(name="TRADE_LOCK", nullable = false)
    private Integer tradeLock;

    @Column(name="URL_IMAGEM_PRODUTO", nullable = false)
    private String urlImagem;

}

