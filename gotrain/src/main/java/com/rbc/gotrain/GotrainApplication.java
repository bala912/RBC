package com.rbc.gotrain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 *  Caching enabled with EnableCaching annotation
 */
@SpringBootApplication
@EnableCaching
public class GotrainApplication {

	public static void main(String[] args) {
		SpringApplication.run(GotrainApplication.class, args);
	}

}
