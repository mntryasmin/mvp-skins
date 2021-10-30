package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItensPedidoCompositeKeyDTO implements Serializable {
    private Long idProduto;
    private PedidoDTO pedido;
}
