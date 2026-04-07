package com.example.SafetyNet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class SafetyNetApplication {


	private static final Logger logger = LogManager.getLogger(SafetyNetApplication.class);

	public static void main(String[] args) {
		logger.info("Starting the application...");
		SpringApplication.run(SafetyNetApplication.class, args);
	}

}
