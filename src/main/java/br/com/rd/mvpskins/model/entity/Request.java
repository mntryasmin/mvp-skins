package br.com.rd.mvpskins.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "TB_PEDIDO")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn
    @Column(nullable = false)
    private Long idCompany;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn
    @Column(nullable = false)
    private Long idClient;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn
    @Column(nullable = false)
    private Long idFormPayment;

    @Column
    private Date issueDate;

    @Column(nullable = false)
    private Double discountProduct;

    @Column(nullable = false)
    private Double grossAddedValue;

    @Column(nullable = false)
    private Double netValue;
}