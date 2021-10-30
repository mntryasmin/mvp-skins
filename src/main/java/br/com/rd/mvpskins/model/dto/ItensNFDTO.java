package br.com.rd.mvpskins.model.dto;

import lombok.Data;

@Data
public class ItensNFDTO {
    private ItensNFCompositeKeyDTO id;
    private Integer quantidade;
    private Double icms;
    private Double pis;
    private Double cofins;
    private Double ipi;
    private Double desconto;
    private Double valorBruto;
    private Double valorLiquido;
}
