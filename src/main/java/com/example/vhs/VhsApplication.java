package com.example.vhs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class VhsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VhsApplication.class, args);
	}

}
