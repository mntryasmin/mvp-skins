package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PromocaoDTO {
    private Long codigoPromocao;
    private String descricao;
    private String cupomDesconto;
    private Float porcentagemDesconto;
    private Float valorDesconto;
    private LocalDate dataFim;
    

}
