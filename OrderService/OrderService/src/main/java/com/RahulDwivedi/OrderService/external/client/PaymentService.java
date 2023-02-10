package com.RahulDwivedi.OrderService.external.client;

import com.RahulDwivedi.OrderService.exception.CustomException;
import com.RahulDwivedi.OrderService.external.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Add circuit breaker here...so that when we are doing our Api call outside, we can have our circuit breaker implemented for that
//name of the circuit breaker and give a fallback method that needs to be called
@CircuitBreaker(name="external",fallbackMethod = "fallback")
//name same as Payment Service in application.yaml file
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {

    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

    //because it is an interface...we can use Java 8 features to create default method here
    //exception as the injection here
    default ResponseEntity<Long> fallback(Exception e) {
        throw new CustomException("Payment Service is not available",
                "UNAVAILABLE",
                500);
    }
}
