package br.com.rd.mvpskins.model.dto;

import lombok.Data;

@Data
public class ItensPedidoDTO {
    private ItensPedidoCompositeKeyDTO id;
    private Integer quantidade;
    private Double desconto;
    private Double valorBruto;
    private Double valorLiquido;
}
