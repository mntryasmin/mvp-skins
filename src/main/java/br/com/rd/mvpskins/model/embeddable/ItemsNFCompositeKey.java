package br.com.rd.mvpskins.model.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ItemsNFCompositeKey implements Serializable {

    @Column(nullable = false)
    private Long idProduct;

    @Column(nullable = false)
    private Long idNF;
}