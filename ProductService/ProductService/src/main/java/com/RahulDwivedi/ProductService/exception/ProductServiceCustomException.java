//we can create multiple exception classes to handle different scenarios

package com.RahulDwivedi.ProductService.exception;

import lombok.Data;

//data annotation to get the boiler plate code
@Data
public class ProductServiceCustomException extends RuntimeException{

    private String errorCode;

    //constructor
    //this particular customException we will throw when data is not available
    public ProductServiceCustomException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
