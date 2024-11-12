package ua.edu.ukma.event_management_system.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.dto.UserDto;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class Ticket {
    private long id;
    private User user;
    private Event event;
    private int price;
    private LocalDateTime purchaseDate;

}
