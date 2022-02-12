package com.demo.tdd;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.tdd.domain.Car;

@RestController
public class CarController {

	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/cars/{name}")
	public Car getCar(@PathVariable String name) {		
		return this.carService.getCarDetails(name);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void carNotFoundHandler(CarNotFoundException ex) {

	}
}
