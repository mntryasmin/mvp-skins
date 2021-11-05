package br.com.rd.mvpskins.model.dto;

import br.com.rd.mvpskins.model.entity.CategoriaPreco;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CompositeKeyPrecoDTO {

    private CategoriaPrecoDTO categoriaPreco;
    private ProdutoDTO produto;
    private LocalDate dtVigencia;
}
