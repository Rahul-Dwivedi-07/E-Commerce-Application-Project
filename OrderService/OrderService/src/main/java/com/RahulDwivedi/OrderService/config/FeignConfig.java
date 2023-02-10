package com.RahulDwivedi.OrderService.config;

import com.RahulDwivedi.OrderService.external.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
In FeignClientConfig , you can create beans of Decoder , Encoder , Logger , Contract , Feign. Builder and Client to override default beans created by Spring Boot.
You can also create beans of Logger. Level , Retryer , ErrorDecoder and RequestInterceptor to include these features.
 */

@Configuration
public class FeignConfig {

    //Whenever Error Decoder is needed it will pass on the CustomErrorDecoder
    @Bean
    ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }
}
