package br.com.rd.mvpskins.model.dto;
import lombok.Data;

import java.util.Date;

@Data
public class PedidoDTO {
    private Long id;
    private ClienteDTO cliente;
    private FormaPagamentoDTO formaPagamento;
    private Date dataRegistro;
    private Double descontoProduto;
    private Double valorBruto;
    private Double valorLiquido;
    private Boolean status;

    public String email(){
        String numeroPedido = this.id.toString();
        String dataCompra = this.dataRegistro.toString();
        String formaPagamento = this.formaPagamento.getDescricao();
        String desconto = this.descontoProduto.toString();
        String valorBruto = this.valorBruto.toString();
        String valorLiquido = this.valorLiquido.toString();
        String status = this.status.toString();

        String textoEmail = "Pedido numero: "+numeroPedido+"\nrealizado em "+dataCompra+
                "\n\nStatus do Pedido: "+status+
                "\n\nForma de Pagamento: "+formaPagamento+"\nDesconto: "+desconto+
                "\nValor da Compra: "+valorBruto+"\nValor Total: "+valorLiquido;

        return textoEmail;
    }
}