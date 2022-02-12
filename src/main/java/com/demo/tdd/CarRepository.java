package com.demo.tdd;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.tdd.domain.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

	Car findByName(String name);

}
