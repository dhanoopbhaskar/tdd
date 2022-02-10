package com.demo.tdd;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.tdd.domain.Car;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CarController.class)
public class CarControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CarService carService;

	@Test
	public void getCar_ShouldReturnCar() throws Exception {
		given(carService.getCarDetails(anyString())).willReturn(new Car("prius", "hybrid"));

		mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius")).andExpect(status().isOk())
				.andExpect(jsonPath("name").value("prius")).andExpect(jsonPath("type").value("hybrid"));
	}

	@Test
	public void getCar_NotFound() throws Exception {
		given(carService.getCarDetails(anyString())).willThrow(new CarNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius")).andExpect(status().isNotFound());
	}
}
