package com.project.springbootmailer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class SpringBootMailerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMailerApplication.class, args);
	}
}
