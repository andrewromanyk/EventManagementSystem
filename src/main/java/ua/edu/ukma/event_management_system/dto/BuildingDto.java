package ua.edu.ukma.event_management_system.dto;

import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.ukma.event_management_system.exceptions.handler.ContentValidator;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BuildingDto {
	//@Min(value = 1, message = "Id is required and must be at least 1")
	private int id;
	@NotBlank(message = "Address is required")
	private String address;
	@Min(value = 1, message = "Hourly rate is required and must be positive")
	private int hourlyRate;
	@Min(value = 10, message = "Area in m2 should be at least 10")
	private int areaM2;
	@Min(value = 10, message = "Capacity should be at least 10")
	private int capacity;
	@Pattern(regexp = "^[A-Za-zА-Яа-я0-9 ,.:;-]+|^$", message = "Description contains invalid characters")
	@Size(max = 500, message = "Description must be less than 500 characters long")
	private String description;
	//@Valid
	private List<BuildingRatingDto> rating = new ArrayList<>();

	public BuildingDto(int id, String address, int hourlyRate, int areaM2, int capacity, String description) {
		this.id = id;
		this.address = address;
		this.hourlyRate = hourlyRate;
		this.areaM2 = areaM2;
		this.capacity = capacity;
		this.description = description;
	}

	public BuildingDto(int id, String address, int hourlyRate, int areaM2,
					   int capacity, String description, List<BuildingRatingDto> rating) {
		this(id, address, hourlyRate, areaM2, capacity, description);
		this.rating = rating;
	}
}
