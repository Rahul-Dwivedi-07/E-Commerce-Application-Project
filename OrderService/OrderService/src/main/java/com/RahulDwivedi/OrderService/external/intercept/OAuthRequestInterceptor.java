//package com.RahulDwivedi.OrderService.external.intercept;
//
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//
////to override configuration for our feign client
//@Configuration
//public class OAuthRequestInterceptor implements RequestInterceptor {
//
//
//    //In the template itself we need to get the authentication token
//    //Authentication token we will get from here OAuth2AuthorizedClientManager
//    @Autowired
//    private OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
//
//    @Override
//    public void apply(RequestTemplate template) {
//        template.header("Authorization", "Bearer "
//                + oAuth2AuthorizedClientManager
//                .authorize(OAuth2AuthorizeRequest
//                        .withClientRegistrationId("internal-client")
//                        //principal is scope
//                        .principal("internal")
//                        .build())
//                .getAccessToken().getTokenValue());
//    }
//}
