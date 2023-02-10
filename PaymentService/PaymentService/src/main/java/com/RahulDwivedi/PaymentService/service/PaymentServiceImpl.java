package com.RahulDwivedi.PaymentService.service;

import com.RahulDwivedi.PaymentService.entity.TransactionDetails;
import com.RahulDwivedi.PaymentService.model.PaymentMode;
import com.RahulDwivedi.PaymentService.model.PaymentRequest;
import com.RahulDwivedi.PaymentService.model.PaymentResponse;
import com.RahulDwivedi.PaymentService.repository.TransactionDetailsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

//because this class is the service layer
//Within this PaymentService we will store the transaction details of each and every order thats why we will create an entity called TransactionDetails.
@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    //In PaymentService we need object of repository layer
    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details: {}", paymentRequest);

        //to save the data in db
        TransactionDetails transactionDetails
                = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();

        transactionDetailsRepository.save(transactionDetails);

        log.info("Transaction Completed with Id: {}", transactionDetails.getId());

        return transactionDetails.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        log.info("Getting payment details for the Order Id: {}", orderId);

        //getting Transaction Details
        TransactionDetails transactionDetails
                //by default findById would be there but not findByOrderId as it is a special method
                //by default we can pass the long as well
                = transactionDetailsRepository.findByOrderId(Long.valueOf(orderId));

        //transaction Details info needs to be changed to Payment Response
        PaymentResponse paymentResponse
                = PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                //value of because it is an enum
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .orderId(transactionDetails.getOrderId())
                .status(transactionDetails.getPaymentStatus())
                .amount(transactionDetails.getAmount())
                .build();

        //at the end pass on the paymentResponse back here
        return paymentResponse;
    }
}
