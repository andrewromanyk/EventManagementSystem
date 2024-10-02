package ua.edu.ukma.event_management_system.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class BuildingRating {

	@Setter
	private long id;

	@Setter
	private Building building;

	@Setter
	private byte rating;

	@Setter
	private User author;

	@Setter
	private String comment;
}
