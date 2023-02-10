package com.RahulDwivedi.OrderService.exception;

import lombok.Data;

//one custom exception that would run every place in our project
@Data
//@Data for getters and setters
public class CustomException extends RuntimeException{

    private String errorCode;
    private int Status;

    public CustomException(String message,String errorCode,int status){
        super(message);
        this.errorCode = errorCode;
        this.Status = status;
    }
}
