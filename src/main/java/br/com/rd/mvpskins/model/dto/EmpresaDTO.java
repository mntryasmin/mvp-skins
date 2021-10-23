package br.com.rd.mvpskins.model.dto;

import lombok.Data;

@Data
public class EmpresaDTO {
    private String telefone;
    private String endereco;
    private String cnpj;
    private String nome;
    private String inscricaoEstadual;
}
