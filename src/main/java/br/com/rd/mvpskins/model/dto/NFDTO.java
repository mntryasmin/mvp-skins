package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NFDTO {
    private Long id;
    private RequestDTO request;
    private TypeNFDTO typeNF;
    private EmpresaDTO company;
    private Long idProvider;
    private ClienteDTO client;
    private FormPaymentDTO formPayment;
    private String accessKey;
    private String noteNumber;
    private Double icms;
    private Double ipi;
    private Double pis;
    private Double cofins;
    private Boolean flagNF;
    private Date issueDate;
    private Double discountProduct;
    private Double grossAddedValue;
    private Double netValue;
}
