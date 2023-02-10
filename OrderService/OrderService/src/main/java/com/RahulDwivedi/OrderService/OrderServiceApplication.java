package com.RahulDwivedi.OrderService;

//import com.RahulDwivedi.OrderService.external.intercept.RestTemplateInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
//whatever Feign Client that we will declare in Order Service, all will get all those auto configurations and all those functionalities
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	/*
	Getting ProductDetails from a Rest Template we need to have object of Rest Template as well
-->Define Rest Template Object
-->We can call the ProductDetails Api or ProductService Api to get a Product based on that Rest Template
	 */

//	@Autowired
//	private ClientRegistrationRepository clientRegistrationRepository;
//
//	@Autowired
//	private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;

	//Define a bean and define a method that would return the Rest Template
	@Bean
	//if there are multiple services this Rest Template could load balance and give that information back
	@LoadBalanced
	//We created a Rest Template and we could use this Rest Template in our Service Layer(Order Service Layer)
	public RestTemplate restTemplate(){

		//because of interceptor
//		RestTemplate restTemplate
//				= new RestTemplate();
//		restTemplate.setInterceptors(
//				Arrays.asList(
//						new RestTemplateInterceptor(
//								clientManager(clientRegistrationRepository
//										,oAuth2AuthorizedClientRepository))));
//		//returning new RestTemplate object
		return new RestTemplate();
	}

//	@Bean
//	//this clientmanager will inject 2 things...will inject the repositories(clientregisterationrepositories)
//	public OAuth2AuthorizedClientManager clientManager(
//			//these 2 things inject
//			ClientRegistrationRepository clientRegistrationRepository,
//			OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
//	) {
//		OAuth2AuthorizedClientProvider oAuth2AuthorizedClientProvider
//				= OAuth2AuthorizedClientProviderBuilder
//				.builder()
//				.clientCredentials()
//				.build();
//
//		//will return this
//		DefaultOAuth2AuthorizedClientManager oAuth2AuthorizedClientManager
//				= new DefaultOAuth2AuthorizedClientManager(
//				clientRegistrationRepository,
//				oAuth2AuthorizedClientRepository);
//
//		oAuth2AuthorizedClientManager.setAuthorizedClientProvider(
//				oAuth2AuthorizedClientProvider
//		);
//
//		return oAuth2AuthorizedClientManager;
//	}
}
