package com.sample.rest.demo.springbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringBootRestApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
		return builder.sources(SpringBootRestApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApplication.class, args);
	}
}