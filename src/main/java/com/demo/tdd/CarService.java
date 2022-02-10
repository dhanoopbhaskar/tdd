package com.demo.tdd;

import com.demo.tdd.domain.Car;

public class CarService {

	private CarRepository carRepository;

	public CarService(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	public Car getCarDetails(String name) {
		// TODO Auto-generated method stub
		Car car = this.carRepository.findByName(name);

		if (car == null) {
			throw new CarNotFoundException();
		}

		return car;
	}

}
