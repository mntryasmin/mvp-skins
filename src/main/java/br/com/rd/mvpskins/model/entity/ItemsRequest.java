package br.com.rd.mvpskins.model.entity;

import br.com.rd.mvpskins.model.embeddable.ItemsRequestCompositeKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TB_ITENS_PEDIDO")
public class ItemsRequest {

    @EmbeddedId
    @Column(nullable = false, name = "codigo_item_pedido")
    private ItemsRequestCompositeKey id;

    @Column(nullable = false, name = "quantidade")
    private Integer quantity;

    @Column(nullable = false, name = "desconto")
    private Double discount;

    @Column(nullable = false, name = "valor_bruto")
    private Double grossAddedValue;

    @Column(nullable = false, name = "valor_liquido")
    private Double netValue;
}
