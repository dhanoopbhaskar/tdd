package com.demo.tdd;

import static org.mockito.BDDMockito.given;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.tdd.domain.Car;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TddApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private CarRepository carRepository;

	@Test
	public void testName() throws Exception {
		// arrange
		given(carRepository.findByName("prius")).willReturn(new Car("prius", "hybrid"));

		// act
		ResponseEntity<Car> response = restTemplate.getForEntity("/cars/prius", Car.class);

		// assert
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getName()).isEqualTo("prius");
		Assertions.assertThat(response.getBody().getType()).isEqualTo("hybrid");
	}

}
