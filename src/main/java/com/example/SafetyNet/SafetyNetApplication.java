package com.example.SafetyNet;

import com.example.SafetyNet.repository.DataHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@SpringBootApplication
public class SafetyNetApplication {


	private static final Logger logger = LogManager.getLogger(SafetyNetApplication.class);

	public static void main(String[] args) {
		logger.info("Starting the application...");
		SpringApplication.run(SafetyNetApplication.class, args);
	}

}
