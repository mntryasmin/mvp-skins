package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name="TB_CONTA_STEAM")
@Data
public class ContaSteam {

    @Id
    @Column(name = "CODIGO_CONTA_STEAM")
    private Long idContaSteam;
    @ManyToOne
    @JoinColumn(name = "CODIGO_EMPRESA")
    private Empresa empresa;
    @Column(name = "SENHA_EMAIL")
    private String senhaEmail;
    @Column(name = "TRADE_LINK")
    private String tradeLink;
}
