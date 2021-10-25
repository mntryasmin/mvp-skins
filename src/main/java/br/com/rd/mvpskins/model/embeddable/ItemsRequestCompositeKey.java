package br.com.rd.mvpskins.model.embeddable;

import br.com.rd.mvpskins.model.entity.Request;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class ItemsRequestCompositeKey implements Serializable {

    @Column(nullable = false, name = "codigo_produto")
    private Long idProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_pedido")
    private Request request;
}
