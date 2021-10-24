package br.com.rd.mvpskins.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NFDTO {
    private Long id;

    private RequestDTO requestDTO;
    private TypeNFDTO typeNFDTO;
    private Long idCompanyDTO;
    private Long idProviderDTO;
    private Long idClientDTO;
    private FormPaymentDTO formPaymentDTO;
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
