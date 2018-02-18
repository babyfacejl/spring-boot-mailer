package com.project.springbootmailer;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class SpringBootMailerApplication {
	final static Logger logger = Logger.getLogger(SpringBootMailerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMailerApplication.class, args);
	}
}
