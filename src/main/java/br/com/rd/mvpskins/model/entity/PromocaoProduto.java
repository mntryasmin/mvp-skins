package br.com.rd.mvpskins.model.entity;

import br.com.rd.mvpskins.model.dto.PromocaoProdutoCompositeKeyDTO;
import br.com.rd.mvpskins.model.embeddable.PromocaoProdutoCompositeKey;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity(name = "tb_promocao_produto")

public class PromocaoProduto {
   @EmbeddedId
   @Column(nullable = false, name = "CODIGO_PROMOCAO_PRODUTO")
   private PromocaoProdutoCompositeKey id;
    @Column(name = "CODIGO_PRODUTO")
    private Long codigoProduto;
    @Column(name = "PORCENTAGEM_DESCONTO")
    private Float porcentagemDesconto;
    @Column(name = "VALOR_DESCONTO")
    private Float valorDesconto;
    @Column (name = "DATA_FIM")
    private Date dataFim;



}
