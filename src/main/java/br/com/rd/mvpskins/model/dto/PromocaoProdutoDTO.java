package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PromocaoProdutoDTO {
    private Long codigoProduto;
    private Float porcentagemDesconto;
    private Float valorDesconto;
    private Date dataFim;
    private PromocaoProdutoCompositeKeyDTO id;


}
