package br.com.rd.mvpskins.model.dto;

import br.com.rd.mvpskins.model.embeddable.ItemsRequestCompositeKey;
import lombok.Data;

@Data
public class ItemsRequestDTO {
    private ItemsRequestCompositeKey id;
    private Integer quantity;
    private Double discount;
    private Double grossAddedValue;
    private Double netValue;
}
