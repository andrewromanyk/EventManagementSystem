package ua.edu.ukma.event_management_system.dto;

import lombok.Data;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.User;

@Data
public class BuildingRatingDto {
	private long id;
	private Building building;
	private byte rating;
	private User author;
	private String comment;
}
