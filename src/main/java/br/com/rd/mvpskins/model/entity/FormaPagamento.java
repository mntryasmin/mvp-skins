package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TB_FORMA_PAGAMENTO")
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_FORMA_PAGAMENTO")
    private Long id;

    @Column(nullable = false, name = "DESCRICAO")
    private String descricao;
}
