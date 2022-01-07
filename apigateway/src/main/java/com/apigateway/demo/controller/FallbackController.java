package com.apigateway.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

	@GetMapping("/producerServiceFallBack")
	public String producerServiceFallBackMethod() {
		return "Producer service is taking long time than expected. Please try again later";
	}
	
	@GetMapping("/consumerServiceFallBack")
	public String consumerServiceFallBackMethod() {
		return "Consumer service is taking long time than expected. Please try again later";
	}
}
