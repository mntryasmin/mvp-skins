package br.com.rd.mvpskins.model.dto;

import br.com.rd.mvpskins.model.embeddable.CompositeKeySubcategoria;
import lombok.Data;

@Data
public class SubcategoriaDTO {

    private CompositeKeySubcategoriaDTO chaveComposta;
    private String Descricao;
}
