package ua.edu.ukma.event_management_system.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.edu.ukma.event_management_system.config.SecurityConfiguration;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.exceptions.handler.ControllerExceptionHandler;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(excludeAutoConfiguration = SecurityConfiguration.class)
@ContextConfiguration(classes = {BuildingController.class, ControllerExceptionHandler.class})
@AutoConfigureMockMvc(addFilters = false)
public class BuildingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ModelMapper modelMapper;

	@MockBean
	private BuildingService buildingService;

	@Test
	void testGetBuilding() throws Exception {
		Building building1 = building1();
		when(buildingService.getBuildingById(1L)).thenReturn(building1);
		when(modelMapper.map(building1, BuildingDto.class)).thenReturn(building1Dto());
		mockMvc.perform(MockMvcRequestBuilders.get("/building/1"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("buildings"))
				.andExpect(model().attribute("buildings", List.of(building1Dto())));
	}

	@Test
	void testGetBuildingWithNonExistingId() throws Exception {
		when(buildingService.getBuildingById(any()))
				.thenThrow(new NoSuchElementException("Building not found: 666"));
		mockMvc.perform(MockMvcRequestBuilders.get("/building/666"))
				.andExpect(status().isNotFound())
				.andExpect(model().attributeExists("error"))
				.andExpect(model().attribute("error", "Building not found: 666"));
	}

	private Building building1() {
		return new Building(
				1,
				"Some street, 1",
				500,
				400,
				50,
				"A building located on Some street, 1"
		);
	}

	private BuildingDto building1Dto() {
		return new BuildingDto(1,
				"Some street, 1",
				500,
				400,
				50,
				"A building located on Some street, 1"
		);
	}
}
