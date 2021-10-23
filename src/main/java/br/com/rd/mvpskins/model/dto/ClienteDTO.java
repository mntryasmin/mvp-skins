package br.com.rd.ProjetoIntegrador.DTO;

import br.com.rd.ProjetoIntegrador.Entity.Genero;
import lombok.Data;

import javax.persistence.Column;
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
