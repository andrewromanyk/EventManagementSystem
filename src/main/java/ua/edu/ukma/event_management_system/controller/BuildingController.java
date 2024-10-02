package ua.edu.ukma.event_management_system.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.BuildingRating;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.BuildingRatingDto;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;

import java.util.List;

@RestController
@RequestMapping("building")
public class BuildingController {

	private ModelMapper modelMapper;
	private BuildingService buildingService;

	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Autowired
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	@GetMapping("/{id}")
	public BuildingDto getBuilding(@PathVariable long id) {
		return toDto(buildingService.getBuildingById(id));
	}

	@GetMapping("/")
	public List<BuildingDto> getBuildings(@RequestParam(required = false) Integer capacity) {
		if (capacity == null) {
			return buildingService.getAllBuildings()
					.stream()
					.map(this::toDto)
					.toList();
		} else {
			return buildingService.getAllByCapacity(capacity)
					.stream()
					.map(this::toDto)
					.toList();
		}
	}

	@PostMapping("/")
	public void createNewBuilding(@RequestBody BuildingDto buildingDto) {
		buildingService.createBuilding(buildingDto);
	}

	@PutMapping("/{id}")
	public void updateBuilding(@PathVariable long id, @RequestBody BuildingDto buildingDto) {
		buildingService.updateBuilding(id, buildingDto);
	}

	@DeleteMapping("/{id}")
	public void deleteBuilding(@PathVariable long id) {
		buildingService.deleteBuilding(id);
	}

	@PostMapping("/{id}/rate")
	public void rateBuilding(
			@PathVariable long id
			) {
	}

	@GetMapping("/{buildingId}/{rating}")
	public List<BuildingRatingDto> getRated(@PathVariable long buildingId, @PathVariable byte rating) {
		return buildingService.getAllByBuildingIdAndRating(buildingId, rating)
				.stream()
				.map(this::toDto)
				.toList();
	}

	private BuildingDto toDto(Building building) {
		return modelMapper.map(building, BuildingDto.class);
	}

	private BuildingRatingDto toDto(BuildingRating buildingRating) {
		return modelMapper.map(buildingRating, BuildingRatingDto.class);
	}
}
