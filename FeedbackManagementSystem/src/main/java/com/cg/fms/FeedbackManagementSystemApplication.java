package com.cg.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({"com.cg.fms.service","com.cg.fms.repository","com.cg.fms.controller"})
@EntityScan("com.cg.fms.entity")
@EnableJpaRepositories("com.cg.fms.repository")
@EnableSwagger2
public class FeedbackManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackManagementSystemApplication.class, args);
	}

}
