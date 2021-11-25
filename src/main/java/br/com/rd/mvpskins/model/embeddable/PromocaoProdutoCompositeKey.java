package br.com.rd.mvpskins.model.embeddable;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.rd.mvpskins.model.dto.PromocaoDTO;
import br.com.rd.mvpskins.model.dto.PromocaoProdutoDTO;
import br.com.rd.mvpskins.model.entity.Produto;
import br.com.rd.mvpskins.model.entity.Promocao;
import br.com.rd.mvpskins.model.entity.PromocaoProduto;
import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
public class PromocaoProdutoCompositeKey implements Serializable {

    @Column(name = "DATA_INICIO", nullable = false)
    private LocalDate dataInicio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_PRODUTO", nullable = false)
    private Produto produto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_PROMOCAO", nullable = false)
    private Promocao promocao;





}

