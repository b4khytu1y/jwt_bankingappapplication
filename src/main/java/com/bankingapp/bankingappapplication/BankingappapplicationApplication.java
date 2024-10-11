package com.bankingapp.bankingappapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories(basePackages = "com.bankingapp.repository")
@EntityScan(basePackages = "com.bankingapp.model")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan(basePackages = {"com.bankingapp"})

public class BankingappapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingappapplicationApplication.class, args);
	}

}
