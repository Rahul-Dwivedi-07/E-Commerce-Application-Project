package com.RahulDwivedi.OrderService.external.decoder;

import com.RahulDwivedi.OrderService.exception.CustomException;
import com.RahulDwivedi.OrderService.external.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
//Error Decoder part of this
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
//tell spring that use this CustomErrorDecoder rather than Decoder
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        //to handle the object mapping
        ObjectMapper objectMapper = new ObjectMapper();

        //simple url and header information whatever we are getting in the response
        log.info("::{}",response.request().url());
        log.info("::{}",response.request().headers());

        //need all data in the form of ErrorResponse.class
        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(),ErrorResponse.class);

            return new CustomException(errorResponse.getErrorMessage(),errorResponse.getErrorCode(),response.status());
        } catch (IOException e) {
            //throw new RuntimeException(e);
            throw new CustomException("Internal Server Error",
                    "INTERNAL_SERVER_ERROR",500);
        }
    }
}
