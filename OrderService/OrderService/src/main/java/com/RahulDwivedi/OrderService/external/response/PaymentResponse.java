package com.RahulDwivedi.OrderService.external.response;

import com.RahulDwivedi.OrderService.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

//Payment Response as POJO we need to have @Data
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {

    //these are the general info that we have stored in our db as well
    private long paymentId;
    private String status;
    private PaymentMode paymentMode;
    private long amount;
    private Instant paymentDate;
    private long orderId;
}
