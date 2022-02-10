package com.demo.tdd;

import org.springframework.data.repository.CrudRepository;

import com.demo.tdd.domain.Car;

public interface CarRepository extends CrudRepository<Car, Long> {

	Car findByName(String name);

}
