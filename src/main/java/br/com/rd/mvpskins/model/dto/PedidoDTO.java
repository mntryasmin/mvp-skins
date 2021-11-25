package br.com.rd.mvpskins.model.dto;
import lombok.Data;

import java.text.SimpleDateFormat;
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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String numeroPedido = this.id.toString();
        String dataCompra = format.format(this.dataRegistro);
        String formaPagamento = this.formaPagamento.getDescricao();
        String desconto = this.descontoProduto.toString();
        String valorBruto = this.valorBruto.toString();
        String valorLiquido = this.valorLiquido.toString();
        String status = this.status.toString();

        String textoEmail = "Pedido numero: "+numeroPedido+"\nrealizado em "+dataCompra+
                "\n\nStatus do Pedido: em andamento"+
                "\n\nForma de Pagamento: "+formaPagamento+"\nDesconto: "+desconto+
                "\nValor da Compra: "+valorBruto+"\nValor Total: "+valorLiquido;

        return textoEmail;
    }
}