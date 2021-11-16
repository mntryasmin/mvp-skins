package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "tb_promocao")

public class Promocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_PROMOCAO",nullable = false)
    private Long  codigoPromocao;

    @Column(name = "DESCRICAO",nullable = false)
    private String descricao;

    @Column (name = "DATA_FIM")
    private Date dataFim;

    @Column(name = "CUPOM_DESCONTO", nullable = false)
    private String cupomDesconto;

    @Column(name = "PORCENTAGEM_DESCONTO")
    private Float porcentagemDesconto;

    @Column(name = "VALOR_DESCONTO")
    private Float valorDesconto;


}
