package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItensPedidoCompositeKeyDTO implements Serializable {
    private ProdutoDTO produto;
    private PedidoDTO pedido;
}
