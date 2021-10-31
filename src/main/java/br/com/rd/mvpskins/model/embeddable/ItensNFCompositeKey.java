package br.com.rd.mvpskins.model.embeddable;

import br.com.rd.mvpskins.model.entity.NF;
import br.com.rd.mvpskins.model.entity.Produto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class ItensNFCompositeKey implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_produto")
    private Produto produto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_nf")
    private NF nf;
}