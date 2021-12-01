package br.com.rd.mvpskins.model.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "TB_ENDERECO_COBRANCA")
public class EnderecoCobranca implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_pedido")
    private Pedido pedido;

    @Column(nullable = false, name = "CEP")
    private String cep;

    @Column(nullable = false, name = "LOGRADOURO")
    private String logradouro;

    @Column(nullable = false, name = "NUMERO")
    private String numero;

    @Column(nullable = false, name = "COMPLEMENTO")
    private String complemento;

    @Column(nullable = false, name = "BAIRRO")
    private String bairro;

    @Column(nullable = false, name = "CIDADE")
    private String cidade;

    @Column(nullable = false, name = "ESTADO")
    private String estado;
}
