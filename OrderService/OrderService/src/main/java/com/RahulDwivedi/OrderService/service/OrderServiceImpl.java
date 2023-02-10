package com.RahulDwivedi.OrderService.service;

//import com.RahulDwivedi.PaymentService.model.PaymentRequest;
import com.RahulDwivedi.OrderService.entity.Order;
import com.RahulDwivedi.OrderService.exception.CustomException;
import com.RahulDwivedi.OrderService.external.client.PaymentService;
import com.RahulDwivedi.OrderService.external.client.ProductService;
import com.RahulDwivedi.OrderService.external.request.PaymentRequest;
import com.RahulDwivedi.OrderService.external.response.PaymentResponse;
import com.RahulDwivedi.OrderService.model.OrderRequest;
import com.RahulDwivedi.OrderService.model.OrderResponse;
import com.RahulDwivedi.OrderService.repository.OrderRepository;
import com.RahulDwivedi.ProductService.model.ProductResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

//this class is service layer
@Service
@Log4j2
public class OrderServiceImpl implements OrderService{

    //this place orderOrder Api needs to connect to the repository layer because whatever the order object or order entity object we are going to create we need to save it in the db, to save it into the db we have to use the repository layer
    //that's why we need object of repository layer
    //Autowired to inject it
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    //Define Rest Template here
    //we have already defined a bean...we could get that bean here as a load balanced
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public long placeOrder(OrderRequest orderRequest) {

        //create the Order Entity and save the data with status Order Created
        //After this we need to call the ProductService to block our products(Reduce the quantity)
        //After that we need to call the PaymentService to complete the payment as well
        //If payment is successful then mark the order as complete else mark it as CANCELLED
        //Place order - Order Created - all other processings
        //we will store the status of payment as well in the db
        //send the id back to client that this is the order that is created
        //Another Api through which will get the order details with the status itself like what is the status, product and all those info

        //logging is imp as with the help pf logging we come to know what is happening in our app
        //orderRequest object also passed
        log.info("Placing Order Request: {}", orderRequest);

        //before saving the order, we want to call the productService
        //using the declarative approach directly called from Product Service
        //Rest Api call using the Feign client
        productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());

        log.info("Creating Order with Status CREATED");

        //Now handling order info here
        //convert orderRequest into orderEntity and save it
        //order is object
        //using builder pattern to create the object
        //order Id will be created directly
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();

        order = orderRepository.save(order);

        //call payment Service to complete the payment
        log.info("Calling Payment Service to complete the payment");

        PaymentRequest paymentRequest
                = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        //PaymentRequest is completed here

        //defining orderStatus here
        String orderStatus = null;

        try {
            //if everything is OK
            paymentService.doPayment(paymentRequest);
            log.info("Payment done Successfully. Changing the Order status to PLACED");
            orderStatus = "PLACED";
        } catch (Exception e) {
            log.error("Error occurred in payment. Changing order status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("Order Places Successfully with Order Id: {}", order.getId());

        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get order details for Order Id : {}", orderId);

        //getting Order info based on the Order Id that we have passed from the Order Repository
        Order order
                = orderRepository.findById(orderId)
                //if not found
                .orElseThrow(() -> new CustomException("Order not found for the order Id:" + orderId,
                        "NOT_FOUND",
                        404));

        //Getting Product information
        log.info("Invoking Product service to fetch the product for id: {}", order.getProductId());

        //return the ProductResponse
        //we got the ProductResponse back based on the Rest Template
        ProductResponse productResponse
                = restTemplate.getForObject(
                        //rather than giving ip address or port info we would give the name of the service
                //calling based on how connected to our Services history
                "http://PRODUCT-SERVICE/product/" + order.getProductId(),
                //we want the data back as ProductResponse.class
                ProductResponse.class
        );

        //once we got the product information, we would get the payment information
        log.info("Getting payment information form the payment Service");
        PaymentResponse paymentResponse = restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/" + order.getId(),
        PaymentResponse.class);


        //set all the info in OrderResponse.ProductDetails class
        //attach the PaymentResponse in the order Response that we have to send back
        //attach all the info in order Response directly
        OrderResponse.ProductDetails productDetails
                = OrderResponse.ProductDetails
                .builder()
                .productName(productResponse.getProductName())
                .productId(productResponse.getProductId())
                .build();

        //payment details also part of Payment Response added
        OrderResponse.PaymentDetails paymentDetails
                = OrderResponse.PaymentDetails
                .builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentStatus(paymentResponse.getStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .paymentMode(paymentResponse.getPaymentMode())
                .build();

        //Creating OrderResponse object
        OrderResponse orderResponse
                = OrderResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .orderDate(order.getOrderDate())
                //productDetails also set
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();

        return orderResponse;
    }
}
