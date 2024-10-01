package ua.edu.ukma.event_management_system.domain;

import lombok.Data;

@Data
public class Ticket {
    private int id;
    private User user;
    private Event event;
    private int price;
}
