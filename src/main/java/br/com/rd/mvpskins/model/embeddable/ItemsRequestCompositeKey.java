package br.com.rd.mvpskins.model.embeddable;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
public class ItemsRequestCompositeKey implements Serializable {

    @Column(nullable = false)
    private Long idProduct;

    @Column(nullable = false)
    private Long idRequest;
}
