package com.RahulDwivedi.PaymentService.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "TRANSACTION_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //primary key
    private long id;

    @Column(name = "ORDER_ID")
    //which order this payment belongs to
    private long orderId;

    @Column(name = "MODE")
    private String paymentMode;

    @Column(name = "REFERENCE_NUMBER")
    //transaction no for a particular transaction
    private String referenceNumber;

    @Column(name = "PAYMENT_DATE")
    //which date the payment happened
    private Instant paymentDate;

    @Column(name = "STATUS")
    //status of payment
    private String paymentStatus;

    @Column(name = "AMOUNT")
    //what amount payment was done
    private long amount;
}
