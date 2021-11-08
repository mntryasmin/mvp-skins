package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NFDTO {
    private Long id;
    private PedidoDTO pedido;
    private TipoNFDTO tipoNF;
    private EmpresaDTO empresa;
    private ClienteDTO cliente;
    private FormaPagamentoDTO formaPagamento;
    private String chaveAcesso;
    private String numeroNF;
    private Double icms;
    private Double ipi;
    private Double pis;
    private Double cofins;
    private Boolean flagNF;
    private Date dataRegistro;
    private Double descontoProduto;
    private Double valorBruto;
    private Double valorLiquido;
}
