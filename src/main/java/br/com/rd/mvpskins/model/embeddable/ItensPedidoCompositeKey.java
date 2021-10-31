package br.com.rd.mvpskins.model.embeddable;

import br.com.rd.mvpskins.model.entity.Pedido;
import br.com.rd.mvpskins.model.entity.Produto;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class ItensPedidoCompositeKey implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_produto")
    private Produto produto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_pedido")
    private Pedido pedido;
}
