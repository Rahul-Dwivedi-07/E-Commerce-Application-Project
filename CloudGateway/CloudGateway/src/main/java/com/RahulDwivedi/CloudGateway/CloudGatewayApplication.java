package com.RahulDwivedi.CloudGateway;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

	//currently it will try to store all the data in one key(user key)
	//it will create one key ,i.e, user key and store all the information against it in redis
	//later we will store user information ,i.e, for particular user id it will store the data...means if we have 10000 users, for all the users it will maintain the no of requests...currently maintaining for just one user key
	//Rate Limitor - how many requests are allowed per user per second
	//for no of users, it will maintain no of requests
	@Bean
	KeyResolver userKeySolver() {
		return exchange -> Mono.just("userKey");
	}

	//spring will know about the circuit breaker that we have added so that the configuration namely CircuitBreaker ORDER-SERVICE etc we have added will be added to the context
	//Add a customizer bean for our Resiliance4j circuit breaker
	//create a bean to be in spring's radar
	@Bean
	//Customizer is what thing to customize
	//Customize Resilience4JCircuitBreakerFactory
	//added configure default for the Resiliance4j circuit builder
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
		//pass on the the default configuration as of now
		//simple customizer added for Resiliance4j circuit breaker factory
		//added default ocnfigurations below
		return factory -> factory.configureDefault(
				id -> new Resilience4JConfigBuilder(id)
						.circuitBreakerConfig(
								CircuitBreakerConfig.ofDefaults()

						).build()
		);
	}

}
