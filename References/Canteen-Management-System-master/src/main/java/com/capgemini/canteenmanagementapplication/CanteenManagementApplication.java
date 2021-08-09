package com.capgemini.canteenmanagementapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CanteenManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanteenManagementApplication.class, args);
	}

}
