package com.infy.main;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.infy.dao", "com.infy.handlers", "com.infy.appBeans" })
@EntityScan(basePackages = "com.infy.models")
public class Execute {
	
	@Value("${spring.application.name}")
	private String name;
	
	private static final Logger log = LoggerFactory.getLogger(Execute.class);
	
	@PostConstruct
	public void init() {
		log.info("Starting the service --> ["+ name +"] <--");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Execute.class, args);
	}
}
