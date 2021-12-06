package br.com.rd.mvpskins.model.dto;

import br.com.rd.mvpskins.service.EnderecoCobrancaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    EnderecoCobrancaService enderecoCobrancaService;

    public String email(Long idPedido){

        EnderecoCobrancaDTO endereco = enderecoCobrancaService.searchAdressByRequest(idPedido);

        return(
                "Endereço de faturamento: \n" +
                "CEP: " + endereco.cep + "\n" +
                "Rua: " + endereco.logradouro + ", nº " + endereco.numero + ", " + endereco.complemento + "\n" +
                "Bairro: " + endereco.bairro + "\n" +
                "Cidade: " + endereco.cidade + " - " + endereco.estado + "\n"
        );
    }
}
