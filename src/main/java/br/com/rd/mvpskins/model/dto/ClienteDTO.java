package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.util.Date;
@Data

public class ClienteDTO {
    private Long codigoCliente;
    private String emailCliente;
    private String nomeCliente;
    private Date dataNascimento;
    private String tradeLink;
    private String senhaCliente;
    private GeneroDTO genero;
    private String numeroTelefone;

}
