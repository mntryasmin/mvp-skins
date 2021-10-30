package br.com.rd.mvpskins.model.embeddable;

import br.com.rd.mvpskins.model.entity.Pedido;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class ItensPedidoCompositeKey implements Serializable {

    @Column(nullable = false, name = "codigo_produto")
    private Long idProduto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_pedido")
    private Pedido pedido;
}
