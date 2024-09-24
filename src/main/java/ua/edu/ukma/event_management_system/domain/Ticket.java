package ua.edu.ukma.event_management_system.domain;

public class Ticket {
    private int id;
    private User user;
    private Event event;
    private int price;

    public Ticket(int id, User user, Event event, int price) {
        this.id = id;
        this.user = user;
        this.event = event;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public int getPrice() {
        return price;
    }
}
