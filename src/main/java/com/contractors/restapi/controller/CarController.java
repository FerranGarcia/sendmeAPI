package com.contractors.restapi.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.contractors.restapi.dal.entity.Car;
import com.contractors.restapi.dal.repository.AccountRepository;
import com.contractors.restapi.dal.repository.CarRepository;
import com.contractors.restapi.util.UserNotFoundException;

@CrossOrigin(allowedHeaders = {"*"})
@RestController
@RequestMapping("/{userId}/cars")
public class CarController {
	private final CarRepository carRepository;

	private final AccountRepository accountRepository;

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@PathVariable String userId, @RequestBody Car input) {
		this.validateUser(userId);
		return this.accountRepository
				.findByUsername(userId)
				.map(account -> {
					Car result = carRepository.save(new Car(account,
							input.brand, input.model));

					HttpHeaders httpHeaders = new HttpHeaders();
					httpHeaders.setLocation(ServletUriComponentsBuilder
							.fromCurrentRequest().path("/{id}")
							.buildAndExpand(result.getId()).toUri());
					return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
				}).get();
	}

	@RequestMapping(value = "/{carId}", method = RequestMethod.GET)
	Car readCar(@PathVariable String userId, @PathVariable Long carId) {
		this.validateUser(userId);
		return this.carRepository.findOne(carId);
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<Car> readCars(@PathVariable String userId) {
		this.validateUser(userId);
		return this.carRepository.findByAccountUsername(userId);
	}

	@Autowired
	CarController(CarRepository carRepository,
			AccountRepository accountRepository) {
		this.carRepository = carRepository;
		this.accountRepository = accountRepository;
	}

	private void validateUser(String userId) {
		this.accountRepository.findByUsername(userId).orElseThrow(
				() -> new UserNotFoundException(userId));
	}
}
