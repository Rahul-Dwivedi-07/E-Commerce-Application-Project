package com.RahulDwivedi.OrderService.external.request;

//the below is a wrong import as we need to import that one which is in OrderService
//import com.RahulDwivedi.PaymentService.model.PaymentMode;
import com.RahulDwivedi.OrderService.model.PaymentMode;
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
