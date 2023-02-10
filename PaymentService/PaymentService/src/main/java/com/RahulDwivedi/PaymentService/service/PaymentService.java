package com.RahulDwivedi.PaymentService.service;

import com.RahulDwivedi.PaymentService.model.PaymentRequest;
import com.RahulDwivedi.PaymentService.model.PaymentResponse;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(String orderId);
}
