package com.demo.tdd;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.tdd.domain.Car;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class CachingTest {
	
	@Autowired
	private CarService carService;
	
	@MockBean
	private CarRepository carRepository;
	
	@Test
	public void testCaching() throws Exception {
		// arrange
		given(carRepository.findByName(anyString())).willReturn(new Car("prius", "hybrid"));
		
		// act
		carService.getCarDetails("prius");
		carService.getCarDetails("prius");
		
		// assert
		verify(carRepository, times(1)).findByName("prius");
	}
}
