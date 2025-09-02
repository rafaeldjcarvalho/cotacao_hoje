package com.rafael.cotacao_hoje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CotacaoHojeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CotacaoHojeApplication.class, args);
	}

}
