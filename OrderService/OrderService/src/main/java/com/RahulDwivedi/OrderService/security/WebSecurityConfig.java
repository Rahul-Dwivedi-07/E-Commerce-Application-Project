//package com.RahulDwivedi.OrderService.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//// Spring Configuration annotation indicates that the class has @Bean definition methods. So Spring container can process the class and generate Spring Beans to be used in the application.
//@Configuration
//@EnableWebSecurity
////we can add most of the configurations at the method level itself
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig {
//
//    //grab info regarding configuration from documentation and override it
//    @Bean
//    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
//        //authorize request for all the HTTP
//        //we will have jwt for oauth2ResourceServer
//        http
//                .authorizeRequests(
//                        authorizeRequest -> authorizeRequest
//                                .anyRequest()
//                                .authenticated())
//                .oauth2ResourceServer(
//                        OAuth2ResourceServerConfigurer::jwt);
//
//        return http.build();
//    }
//}
