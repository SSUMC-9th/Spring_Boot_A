package com.example.foodmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FoodMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodMasterApplication.class, args);
	}

}
