package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "TB_PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "CODIGO_PEDIDO")
    private Long id;

    @Column (name = "DATA_EMISSAO")
    private Date dataRegistro;

    @Column(nullable = false, name = "DESCONTO_PRODUTO")
    private Double descontoProduto;

    @Column(nullable = false, name = "VALOR_TOTAL_BRUTO")
    private Double valorBruto;

    @Column(nullable = false, name = "VALOR_TOTAL_LIQUIDO")
    private Double valorLiquido;
}