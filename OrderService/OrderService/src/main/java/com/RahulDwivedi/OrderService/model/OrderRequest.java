package com.RahulDwivedi.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//OrderRequest with all the fields and implementations that we need
@Data
@AllArgsConstructor
@NoArgsConstructor
//builder pattern for this class
@Builder
public class OrderRequest {

    private long productId;
    private long totalAmount;
    private long quantity;
    //how payment is done for this order
    //this PaymentMode is an enum
    private PaymentMode paymentMode;
}
