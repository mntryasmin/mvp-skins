package br.com.rd.mvpskins.model.dto;

import lombok.Data;

@Data
public class ItemsRequestDTO {
    private ItemsRequestCompositeKeyDTO id;
    private Integer quantity;
    private Double discount;
    private Double grossAddedValue;
    private Double netValue;
}
