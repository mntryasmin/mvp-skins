package br.com.rd.mvpskins.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity(name = "tb_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_CLIENTE")
    private Long codigoCliente;
    @Column(name = "EMAIL",nullable = false)
    private String emailCliente;
    @Column(name = "NOME",nullable = false)
    private String nomeCliente;
    @Column(name = "DATA_NASCIMENTO",nullable = false)
    private Date dataNascimento;
    @Column(name = "TRADE_LINK",nullable = false)
    private String tradeLink;
    @Column(name = "SENHA",nullable = false)
    private String senhaCliente;
    @Column(name = "TELEFONE", nullable = false)
    private String numeroTelefone;

    @ManyToOne
    @JoinColumn(name = "CODIGO_GENERO")
    public Genero genero;


}
