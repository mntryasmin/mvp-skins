package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "TB_EMPRESA")
@Data
public class Empresa {
    @Id
    @Column(name = "CODIGO_EMPRESA")
    private Long idEmpresa;
    @Column(name = "ENDERECO")
    private String endereco;
    @Column(name = "CNPJ")
    private String cnpj;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "INSCRICAO_ESTADUAL")
    private String inscricaoEstadual;
    @Column(name = "TELEFONE")
    private String telefone;


}
