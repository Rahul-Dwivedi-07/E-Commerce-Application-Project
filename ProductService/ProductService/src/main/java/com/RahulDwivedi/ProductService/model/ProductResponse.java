package com.RahulDwivedi.ProductService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//data for all the getters and setters
@Data
//we can use this class with builder pattern to build our object as we have multiple fields here
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String productName;
    private long productId;
    private long quantity;
    private long price;

}
