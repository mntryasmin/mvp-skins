package br.com.rd.mvpskins.model.entity;

import br.com.rd.mvpskins.model.embeddable.ItemsRequestCompositeKey;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TB_ITENS_PEDIDOS")
public class ItemsRequest {

    @EmbeddedId
    @Column(nullable = false)
    private ItemsRequestCompositeKey id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private Double grossAddedValue;

    @Column(nullable = false)
    private Double netValue;
}
