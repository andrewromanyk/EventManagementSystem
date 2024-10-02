package ua.edu.ukma.event_management_system.domain;

import lombok.Data;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.dto.UserDto;

@Data
public class Ticket {
    private long id;
    private UserDto user;
    private EventDto event;
    private int price;
}
