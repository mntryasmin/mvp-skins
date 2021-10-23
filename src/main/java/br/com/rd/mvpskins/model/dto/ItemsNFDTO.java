package br.com.rd.mvpskins.model.dto;

import lombok.Data;

@Data
public class ItemsNFDTO {
    private ItemsNFCompositeKeyDTO id;
    private Integer quantity;
    private Double icms;
    private Double pis;
    private Double cofins;
    private Double ipi;
    private Double discount;
    private Double grossAddedValue;
    private Double netValue;
}
