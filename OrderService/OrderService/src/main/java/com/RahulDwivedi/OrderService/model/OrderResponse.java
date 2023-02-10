package com.RahulDwivedi.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    //fields in this class
    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
    //added object of it
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    //Static inner class
    //this is inner class just for simplicity but ideally we would be having a different class and call accordingly
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductDetails {

        private String productName;
        private long productId;
        private long quantity;
        private long price;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PaymentDetails{
        private long paymentId;
        private PaymentMode paymentMode;
        private String paymentStatus;
        private Instant paymentDate;
    }

}
