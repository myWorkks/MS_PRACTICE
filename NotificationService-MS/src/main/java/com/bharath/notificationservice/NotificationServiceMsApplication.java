package com.bharath.notificationservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationServiceMsApplication {
private static final Logger logger=LoggerFactory.getLogger(NotificationServiceMsApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceMsApplication.class, args);
	}
@KafkaListener(topics="notify")
public void handleNotification(String notification) {
	logger.info("notification received {}",notification);
}
}
