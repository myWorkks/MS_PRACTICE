package com.bharath.cartms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "cart-api")
public class CartController {
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);

	@GetMapping(value = "/get")

	public String getFullName() {
		logger.info("wait started");
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			logger.info("exception occured {}", e.getMessage());

		}
		logger.info("wait completed");
		return "bharath".toUpperCase();
	}
}
