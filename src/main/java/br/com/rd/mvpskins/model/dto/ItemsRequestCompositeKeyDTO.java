package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemsRequestCompositeKeyDTO implements Serializable {
    private Long idProduct;
    private RequestDTO request;
}
