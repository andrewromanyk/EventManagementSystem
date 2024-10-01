package ua.edu.ukma.event_management_system.domain;

import lombok.Data;

@Data
public class BuildingRating {
	private long id;
	private Building building;
	private byte rating;
	private User author;
	private String comment;
}
