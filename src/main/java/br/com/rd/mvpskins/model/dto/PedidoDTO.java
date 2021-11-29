package br.com.rd.mvpskins.model.dto;
import lombok.Data;

import java.text.NumberFormat;
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
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        String numeroPedido = this.id.toString();
        String dataCompra = format.format(this.dataRegistro);
        String formaPagamento = this.formaPagamento.getDescricao();
        String desconto = numberFormat.format(this.descontoProduto);
        String valorBruto = numberFormat.format(this.valorBruto);
        String valorLiquido = numberFormat.format(this.valorLiquido);
        String status = this.status.toString();

        String textoEmail = "Pedido número: "+numeroPedido+"\nrealizado em "+dataCompra+
                "\n\nStatus do Pedido: em andamento"+
                "\n\nForma de Pagamento: "+formaPagamento+
                "\nValor da Compra: "+valorBruto+
                "\nDesconto: "+desconto+
                "\nValor Total: "+valorLiquido;

        return textoEmail;
    }
}