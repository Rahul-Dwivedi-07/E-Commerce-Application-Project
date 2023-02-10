package com.RahulDwivedi.PaymentService.controller;

import com.RahulDwivedi.PaymentService.model.PaymentRequest;
import com.RahulDwivedi.PaymentService.model.PaymentResponse;
import com.RahulDwivedi.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    //ResponseEntity of Type Long
    //details need to passed form order service as the request body through a class in bracket of doPayment()
    //in model we have class PaymentRequest
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(
                //will take payment request
                paymentService.doPayment(paymentRequest),
                HttpStatus.OK);
    }

    //new Api
    //passing PaymentResponse
    //mandatory field that's why passing as a Path Variable
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable String orderId) {
        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByOrderId(orderId),
                HttpStatus.OK
        );
    }

}
