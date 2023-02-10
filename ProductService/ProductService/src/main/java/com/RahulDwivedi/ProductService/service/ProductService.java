package com.RahulDwivedi.ProductService.service;

import com.RahulDwivedi.ProductService.model.ProductRequest;
import com.RahulDwivedi.ProductService.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
