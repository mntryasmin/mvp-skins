package br.com.rd.mvpskins.model.embeddable;

import br.com.rd.mvpskins.model.entity.Categoria;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class CompositeKeySubcategoria implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_CATEGORIA")
    private Categoria categoria;

    @Column(name="CODIGO_SUBCATEGORIA")
    private Long codigoSubcategoria;
}
