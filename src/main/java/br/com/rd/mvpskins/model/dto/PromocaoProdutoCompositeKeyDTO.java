package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class PromocaoProdutoCompositeKeyDTO implements Serializable {
    private ProdutoDTO produto;
    private PromocaoDTO promocao;
    private Date dataInicio;
}
