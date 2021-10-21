package br.com.rd.mvpskins.model.dto;

import lombok.Data;

@Data
public class PedidoDTO {
    private Long id;
    private Long nf;
    private Integer quantidade;
    private Double icms;
    private Double pis;
    private Double cofins;
    private Double ipi;
    private Double desconto;
    private Double valor_bruto;
    private Double valor_liquido;
}