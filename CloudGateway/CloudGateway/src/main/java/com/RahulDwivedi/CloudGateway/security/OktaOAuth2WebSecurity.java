//package com.RahulDwivedi.CloudGateway.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
////because it is a reactive app
//@EnableWebFluxSecurity
//public class OktaOAuth2WebSecurity {
//
//    //override configuration using filter
//    //for this http we can ust the security filter chain and pass on the information to override
//    @Bean
//    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange()
//                .anyExchange().authenticated()
//                .and()
//                //load default login page of okta
//                .oauth2Login()
//                .and()
//                //for our resource servers we should be accessing the jwts
//                .oauth2ResourceServer()
//                //giving jwt for us
//                .jwt();
//
//        return http.build();
//    }
//}
