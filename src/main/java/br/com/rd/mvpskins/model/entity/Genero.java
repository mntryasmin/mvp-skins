package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "tb_genero")
public class Genero {
    @Id
    @Column(name ="CODIGO_GENERO")
    private Long codigoGenero;
    @Column(name="NOME_GENERO")
    private String nomeGenero;



}
