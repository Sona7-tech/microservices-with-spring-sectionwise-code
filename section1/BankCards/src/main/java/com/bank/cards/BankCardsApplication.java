package com.bank.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScans({ @ComponentScan("com.bank.cards.controller") })
@EnableJpaRepositories("com.bank.cards.repository")
@EntityScan("com.bank.cards.model")
public class BankCardsApplication {

	public static void main(String[] args) {

		SpringApplication.run(BankCardsApplication.class, args);
	}

}
