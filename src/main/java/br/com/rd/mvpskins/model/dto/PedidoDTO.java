package br.com.rd.mvpskins.model.dto;
import lombok.Data;

import java.util.Date;

@Data
public class PedidoDTO {
    private Long id;
    private EmpresaDTO empresa;
    private ClienteDTO cliente;
    private FormaPagamentoDTO formaPagamento;
    private Date dataRegistro;
    private Double descontoProduto;
    private Double valorBruto;
    private Double valorLiquido;
}