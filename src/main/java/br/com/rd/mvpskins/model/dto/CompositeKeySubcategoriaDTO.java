package br.com.rd.mvpskins.model.dto;

import br.com.rd.mvpskins.model.entity.Categoria;
import lombok.Data;

@Data
public class CompositeKeySubcategoriaDTO {

    private CategoriaDTO categoria;
    private Long codigoSubcategoria;
}
