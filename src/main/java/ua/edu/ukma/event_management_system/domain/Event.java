package ua.edu.ukma.event_management_system.domain;

import lombok.Data;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.service.interfaces.Ratable;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Event {
    private int id;
    private String eventTitle;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private Building building;
    private String description;
    private int numberOfTickets;
    private int minAgeRestriction;
    private List<EventRating> rating;
    private List<UserDto> users;
}
