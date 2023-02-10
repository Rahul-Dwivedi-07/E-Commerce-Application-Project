package com.RahulDwivedi.PaymentService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//get all the parameterized constructors
@AllArgsConstructor
//get default constructors
@NoArgsConstructor
@Builder
public class PaymentRequest {

    private long orderId;
    private long amount;
    private String referenceNumber;
    //type of payment
    private PaymentMode paymentMode;

}
