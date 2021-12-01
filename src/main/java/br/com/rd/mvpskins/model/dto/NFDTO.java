package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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

    public String email(){

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

        String numeroNota = this.numeroNF.toString();
        String emissor = this.empresa.getNome();
        String chaveAcesso = this.chaveAcesso;
        String icms = numberFormat.format(this.icms);
        String ipi = numberFormat.format(this.ipi);
        String pis = numberFormat.format(this.pis);
        String cofins = numberFormat.format(this.cofins);
        String dataEmissao = format.format(this.dataRegistro);
        String desconto = numberFormat.format(this.descontoProduto);
        String valorBruto = numberFormat.format(this.valorBruto);
        String valorLiquido = numberFormat.format(this.valorLiquido);

        String textoEmail = "Número da Nota: "+numeroNota+
                "\nChave de Acesso: "+chaveAcesso+
                "\nEmissor: "+emissor+
                "\nemitida em "+dataEmissao+
                "\n\nValor Bruto: "+valorBruto+
                "\n\nImpostos "+
                "\nICMS: "+icms+
                "\nIPI: "+ipi+
                "\nPIS: "+pis+
                "\nCOFINS: "+cofins+
                "\n\nDesconto: "+desconto+
                "\n\nValor Líquido: "+valorLiquido;

        return textoEmail;
    }
}
