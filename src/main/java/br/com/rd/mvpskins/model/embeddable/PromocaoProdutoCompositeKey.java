package br.com.rd.mvpskins.model.embeddable;

import java.io.Serializable;
import java.util.Date;

import br.com.rd.mvpskins.model.dto.PromocaoDTO;
import br.com.rd.mvpskins.model.dto.PromocaoProdutoDTO;
import br.com.rd.mvpskins.model.entity.Promocao;
import br.com.rd.mvpskins.model.entity.PromocaoProduto;
import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
public class PromocaoProdutoCompositeKey implements Serializable {

    @Column(name = "DATA_INICIO")
    private Date dataInicio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_PRODUTO")
    private PromocaoProduto promocaoProduto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_PROMOCAO")
    private Promocao promocao;

    public Date getDataInicio() {
        return dataInicio;

    }



}

