package com.RahulDwivedi.ProductService.exception;

import com.RahulDwivedi.ProductService.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//ControllerAdvice meaning whenever there will be an exception in our controller, it will handle it
public class RestResponseEnityExceptionHandler extends ResponseEntityExceptionHandler {

    //pass custom object as well
    @ExceptionHandler(ProductServiceCustomException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceCustomException exception) {
        //create object of Error Response and send back
        //builder maybe because of @Builder (from my side)
        //pass status code at the end
        //Handler created for ProductServiceCustomException
        //so whenever product is not found we will send the error message and status not found...we would also send a particular error code from which the calling service would understand what is the issue with the api
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(), HttpStatus.NOT_FOUND);
    }
}
