package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PromocaoDTO {
    private Long codigoPromocao;
    private String descricao;
    private String cupomDesconto;
    private Float porcentagemDesconto;
    private Float valorDesconto;
    private Date dataFim;
    

}
