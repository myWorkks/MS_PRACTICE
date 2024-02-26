package com.bharath.productms.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RequestMapping(value = "/product-api")
@RestController
public class ProductController {

	@Autowired
	private WebClient.Builder webclient;

	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
//topic datatype,object datatype
	@GetMapping(value = "/get")
	@CircuitBreaker(name = "cart", fallbackMethod = "fallbackMethod")
	@TimeLimiter(name = "cart")
	@Retry(name = "cart")
	public CompletableFuture<String> getFullName() {
		String firstNameFromOrder = webclient.build().get().uri("http://cartservice/cart-api/get").retrieve()
				.bodyToMono(String.class).block();
		// ResponseEntity<String> firstNameFromOrder= new
		// RestTemplate().getForEntity("http://localhost:1010/cart-api/get",String.class)
		// ;
		
		
		kafkaTemplate.send("notify","checking the kafka in MS");
		return CompletableFuture.supplyAsync(() -> firstNameFromOrder + " " + "reddy");
	}

	public CompletableFuture<String> fallbackMethod(RuntimeException e) {
		return CompletableFuture.supplyAsync(() -> "OOps! something went wrong");
	}
}

//String firstNameFromOrder=		webclient.build().get().uri("http://cartservice/cart-api/get",
//		uriBuilder->uriBuilder.queryParam("first_name", firstName).build()).retrieve().bodyToMono(String.class).block();