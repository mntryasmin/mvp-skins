package br.com.rd.mvpskins.model.dto;

import lombok.Data;

@Data
public class EnderecoCobrancaDTO {
    private Long id;
    private PedidoDTO pedido;
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
}
