package br.com.rd.mvpskins.model.dto;
import lombok.Data;

import java.util.Date;

@Data
public class RequestDTO {
    private Long id;
    private Long idCompany;
    private Long idClient;
    private FormPaymentDTO FormPaymentDTO;
    private Date issueDate;
    private Double discountProduct;
    private Double grossAddedValue;
    private Double netValue;
}