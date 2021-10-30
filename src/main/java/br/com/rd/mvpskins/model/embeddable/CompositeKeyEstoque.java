package br.com.rd.mvpskins.model.embeddable;

import br.com.rd.mvpskins.model.entity.ContaSteam;
import br.com.rd.mvpskins.model.entity.Produto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
public class CompositeKeyEstoque  implements Serializable {

    @ManyToOne
    @JoinColumn(name = "CODIGO_PRODUTO")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "CODIGO_CONTA_STEAM")
    private ContaSteam contaSteam;
}
