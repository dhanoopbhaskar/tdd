package com.demo.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.tdd.domain.Car;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CarRepositoryTest {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void findByName_ReturnsCarDetails() throws Exception {
		// arrange
		Car savedCar = entityManager.persistFlushFind(new Car("prius", "hybrid"));
		
		// act
		Car car = carRepository.findByName("prius");
		
		// assert
		assertThat(car.getName()).isEqualTo(savedCar.getName());
		assertThat(car.getType()).isEqualTo(savedCar.getType());
	}
}
