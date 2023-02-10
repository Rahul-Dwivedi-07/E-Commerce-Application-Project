package com.RahulDwivedi.OrderService.service;

import com.RahulDwivedi.OrderService.model.OrderRequest;
import com.RahulDwivedi.OrderService.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
