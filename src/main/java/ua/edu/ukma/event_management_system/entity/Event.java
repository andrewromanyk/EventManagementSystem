package ua.edu.ukma.event_management_system.entity;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private String eventTitle;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private Building building;
    private String description;
    private int numberOfTickets;
    private int minAgeRestriction;

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
}
