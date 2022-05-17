package com.management.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WorkManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkManagementApplication.class, args);
	}

}
