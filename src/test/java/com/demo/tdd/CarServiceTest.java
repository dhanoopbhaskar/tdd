package com.demo.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.tdd.domain.Car;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

	@Mock
	private CarRepository carRepository;
	private CarService carService;

	@BeforeEach
	public void setUp() throws Exception {
		carService = new CarService(carRepository);
	}

	@Test
	public void getCarDetails_ShouldReturnCarInfo() throws Exception {
		// arrange
		given(carRepository.findByName("prius")).willReturn(new Car("prius", "hybrid"));

		// act
		Car car = carService.getCarDetails("prius");

		// assert
		assertThat(car.getName()).isEqualTo("prius");
		assertThat(car.getType()).isEqualTo("hybrid");
	}

	@Test
	public void getCarDetails_WhenNotFound() throws Exception {
		// arrange
		given(carRepository.findByName("prius")).willReturn(null);

		// assert
		assertThrows(CarNotFoundException.class, () -> {
			// act
			carService.getCarDetails("prius");
		});
	}
}
