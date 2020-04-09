package com.billy.billyServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BillyServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillyServicesApplication.class, args);
	}

}
