package com.cg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {
	private static final Logger LOGGER = LogManager.getLogger("MyApplication.class");

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
		LOGGER.info("My Itinerary Application Started.");
	}
}
