package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemsNFCompositeKeyDTO implements Serializable {
    private Long idProduct;
    private NFDTO nf;
}
