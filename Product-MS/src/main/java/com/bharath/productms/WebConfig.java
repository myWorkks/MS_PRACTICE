package com.bharath.productms;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfig {

	@Bean
	@LoadBalanced
	WebClient.Builder getWebCLientBuilder(){
		return WebClient.builder();
	}
}
