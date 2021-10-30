package br.com.rd.mvpskins.model.entity;

import br.com.rd.mvpskins.model.embeddable.ItensPedidoCompositeKey;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity(name = "TB_ITENS_PEDIDO")
public class ItensPedido {

    @EmbeddedId
    @Column(nullable = false, name = "CODIGO_ITEM_PEDIDO")
    private ItensPedidoCompositeKey id;

    @Column(nullable = false, name = "QUANTIDADE")
    private Integer quantidade;

    @Column(nullable = false, name = "DESCONTO")
    private Double desconto;

    @Column(nullable = false, name = "VALOR_BRUTO")
    private Double valorBruto;

    @Column(nullable = false, name = "VALOR_LIQUIDO")
    private Double valorLiquido;
}
