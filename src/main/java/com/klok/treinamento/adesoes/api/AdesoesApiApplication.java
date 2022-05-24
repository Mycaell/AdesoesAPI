package com.klok.treinamento.adesoes.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AdesoesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdesoesApiApplication.class, args);
	}

}
