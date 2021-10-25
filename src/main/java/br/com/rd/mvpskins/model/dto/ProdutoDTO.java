package br.com.rd.mvpskins.model.dto;

import br.com.rd.mvpskins.model.entity.Categoria;
import lombok.Data;

@Data
public class ProdutoDTO {

    private Long id;
    private String descricao;
    private CategoriaDTO categoria;
    private SubcategoriaDTO subcategoria;
    private ColecaoDTO colecao;
    private RaridadeDTO raridade;
    private ExteriorDTO exterior;
    private Double desgaste;
    private Integer pattern;
    private Integer tradeLock;
    private String urlImagem;
}
