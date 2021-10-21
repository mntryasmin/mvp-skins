package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
public class PedidoDTO {
    private Long id;
    private Integer codigo_empresa;
    private Integer codigo_cliente;
    private Integer codigo_forma_pagamento;
    private Date data_emissao;
    private Double desconto_produto;
    private Double valor_bruto;
    private Double valor_liquido;
}