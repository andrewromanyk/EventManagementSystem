package ua.edu.ukma.event_management_system.entity;

import ua.edu.ukma.event_management_system.service.interfaces.Ratable;

import java.time.LocalDateTime;

public class Event implements Ratable {
    private int id;
    private String eventTitle;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private Building building;
    private String description;
    private int numberOfTickets;
    private int minAgeRestriction;
    private int rating;

    public Event(int id, String eventTitle, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, Building building, String description, int numberOfTickets, int minAgeRestriction) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.building = building;
        this.description = description;
        this.numberOfTickets = numberOfTickets;
        this.minAgeRestriction = minAgeRestriction;
    }

    public int getId() {
        return id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public Building getBuilding() {
        return building;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public int getMinAgeRestriction() {
        return minAgeRestriction;
    }

    @Override
    public void rate(int rating) {
        if (rating < 1)
            this.rating = 1;
        else if(rating > 100)
            this.rating = 100;
        else
            this.rating = rating;
    }

    @Override
    public int getRating() {
        return rating;
    }
}
