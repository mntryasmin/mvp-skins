package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data

public class ClienteDTO {
    private Long codigoCliente;
    private String emailCliente;
    private String nomeCliente;
    private LocalDate dataNascimento;
    private String tradeLink;
    private String senhaCliente;
    private String numeroTelefone;
    private GeneroDTO genero;

}
