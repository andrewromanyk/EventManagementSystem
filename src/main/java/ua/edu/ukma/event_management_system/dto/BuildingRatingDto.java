package ua.edu.ukma.event_management_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.User;

@Data
@NoArgsConstructor
public class BuildingRatingDto {
	private long id;
	private byte rating;
	private User author;
	private String comment;
}