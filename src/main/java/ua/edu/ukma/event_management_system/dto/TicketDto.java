package ua.edu.ukma.event_management_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.domain.User;

@Data
@NoArgsConstructor
public class TicketDto {
    private long id;
    private UserDto user;
    private EventDto event;
    private int price;

    public TicketDto(long id, UserDto user, EventDto event, int price) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.price = price;
    }
}
