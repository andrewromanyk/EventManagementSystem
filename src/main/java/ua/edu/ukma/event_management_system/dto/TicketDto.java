package ua.edu.ukma.event_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.domain.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private long id;
    private UserDto user;
    private EventDto event;
    private int price;

}
