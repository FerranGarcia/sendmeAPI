package com.contractors.restapi;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.contractors.restapi.dal.entity.Account;
import com.contractors.restapi.dal.entity.Car;
import com.contractors.restapi.dal.repository.AccountRepository;
import com.contractors.restapi.dal.repository.CarRepository;

@SpringBootApplication
@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
@PropertySource("classpath:application.properties")
public class RestApiApplication {	
	@Bean
	CommandLineRunner init(AccountRepository accountRepository,
			CarRepository carRepository) {
		carRepository.deleteAll();
		accountRepository.deleteAll();
		return (evt) -> Arrays.asList(
				"sbarquero,ctanas,fgarcia".split(","))
				.forEach(
						a -> {
							Account account = accountRepository.save(new Account(a,
									"password"));
							carRepository.save(new Car(account,
									"Audi", "A8 personalized to: "  + a));
							carRepository.save(new Car(account,
									"BMW", "i8 personalized to: " + a));
						});
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
}
