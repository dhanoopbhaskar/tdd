package com.demo.tdd;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.tdd.domain.Car;

@Service
public class CarService {

	private CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Cacheable("cars")
	public Car getCarDetails(String name) {
		// TODO Auto-generated method stub
		Car car = this.carRepository.findByName(name);

		if (car == null) {
			throw new CarNotFoundException();
		}

		return car;
	}

}
