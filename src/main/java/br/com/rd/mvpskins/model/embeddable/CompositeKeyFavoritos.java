package br.com.rd.mvpskins.model.embeddable;

import br.com.rd.mvpskins.model.entity.Cliente;
import br.com.rd.mvpskins.model.entity.Produto;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class CompositeKeyFavoritos implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CODIGO_PRODUTO", insertable = false, updatable = false)
    private Produto produto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CODIGO_CLIENTE", insertable = false, updatable = false)
    private Cliente cliente;
}
