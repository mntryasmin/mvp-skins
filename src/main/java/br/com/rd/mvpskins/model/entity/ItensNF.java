package br.com.rd.mvpskins.model.entity;

import br.com.rd.mvpskins.model.embeddable.ItensNFCompositeKey;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity(name = "TB_ITENS_NF")
public class ItensNF {

    @EmbeddedId
    @Column(nullable = false)
    private ItensNFCompositeKey id;

    @Column(nullable = false, name = "QUANTIDADE")
    private Integer quantidade;

    @Column(nullable = false)
    private Double icms;

    @Column(nullable = false)
    private Double pis;

    @Column(nullable = false)
    private Double cofins;

    @Column(nullable = false)
    private Double ipi;

    @Column(nullable = false, name = "DESCONTO")
    private Double desconto;

    @Column(nullable = false, name = "VALOR_BRUTO")
    private Double valorBruto;

    @Column(nullable = false, name = "VALOR_LIQUIDO")
    private Double valorLiquido;
}