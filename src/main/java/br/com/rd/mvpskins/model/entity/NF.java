package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "TB_NF")
public class NF {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "codigo_nf")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_pedido")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_tipo_nf")
    private TipoNF tipoNF;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_empresa")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_CLIENTE")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CODIGO_FORMA_PAGAMENTO")
    private FormaPagamento formaPagamento;

    @Column(nullable = false, name = "CHAVE_DE_ACESSO")
    private String chaveAcesso;

    @Column(nullable = false, name = "NUMERO_NOTA")
    private String numeroNF;

    @Column(nullable = false)
    private Double icms;

    @Column(nullable = false)
    private Double ipi;

    @Column(nullable = false)
    private Double pis;

    @Column(nullable = false)
    private Double cofins;

    @Column(nullable = false, name = "FLAG_NOTA_PAULISTA")
    private Boolean flagNF;

    @Column (name = "DATA_EMISSAO")
    private Date dataRegistro;

    @Column(nullable = false, name = "DESCONTO_COMPRA")
    private Double descontoCompra;

    @Column(nullable = false, name = "VALOR_TOTAL_BRUTO")
    private Double valorBruto;

    @Column(nullable = false, name = "VALOR_TOTAL_LIQUIDO")
    private Double valorLiquido;
}