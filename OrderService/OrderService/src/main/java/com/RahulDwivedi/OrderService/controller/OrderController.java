package com.RahulDwivedi.OrderService.controller;

import com.RahulDwivedi.OrderService.model.OrderRequest;
import com.RahulDwivedi.OrderService.model.OrderResponse;
import com.RahulDwivedi.OrderService.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
//to add loggers
@Log4j2
public class OrderController {

    @Autowired
    //object of business layer that is OrderService
    private OrderService orderService;

    //return type as Long - we want to return that particular order Id generated
//    @PreAuthorize("hasAuthority('Customer')")
    @PostMapping("/placeOrder")
    //we need to pass the request body
    //will entirely come as a request body for our api
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
        //call our business layer to place the order and return type here is long
        long orderId = orderService.placeOrder(orderRequest);
        //once we get Order Id
        log.info("Order Id: {}", orderId);
        //once we get the order Id, we need to wrap it with response entity and send it back
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    //Calling different services using Rest Template
    @GetMapping("/{orderId}")
    //not internal because this api would be calling different services
//    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer')")
    //pass all the info as response that's why we need object of OrderResponse
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId) {
        //we need object of OrderResponse here
        OrderResponse orderResponse
                = orderService.getOrderDetails(orderId);

        return new ResponseEntity<>(orderResponse,
                HttpStatus.OK);
    }
}
