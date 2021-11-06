package br.com.rd.mvpskins.model.embeddable;

import br.com.rd.mvpskins.model.entity.CategoriaPreco;
import br.com.rd.mvpskins.model.entity.Produto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Data
public class CompositeKeyPreco implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CODIGO_CATEGORIA_PRECO",
            insertable = false,
            updatable = false)
    private CategoriaPreco categoriaPreco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CODIGO_PRODUTO",
            insertable = false,
            updatable = false)
    private Produto produto;

    @Column(name="DATA_VIGENCIA", nullable = false)
    private LocalDate dtVigencia;
}
