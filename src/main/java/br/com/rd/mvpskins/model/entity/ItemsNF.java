package br.com.rd.mvpskins.model.entity;

import br.com.rd.mvpskins.model.embeddable.ItemsNFCompositeKey;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity(name = "TB_ITENS_NF")
public class ItemsNF {

    @EmbeddedId
    @Column(nullable = false)
    private ItemsNFCompositeKey id;

    @Column(nullable = false, name = "quantidade")
    private Integer quantity;

    @Column(nullable = false)
    private Double icms;

    @Column(nullable = false)
    private Double pis;

    @Column(nullable = false)
    private Double cofins;

    @Column(nullable = false)
    private Double ipi;

    @Column(nullable = false, name = "desconto")
    private Double discount;

    @Column(nullable = false, name = "valor_bruto")
    private Double grossAddedValue;

    @Column(nullable = false, name = "valor_liquido")
    private Double netValue;
}