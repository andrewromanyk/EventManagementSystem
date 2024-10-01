package ua.edu.ukma.event_management_system.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Building {
    @Getter
    private int id;
    @Getter
    private String address;
    @Getter
    private int hourlyRate;
    @Getter
    private int areaM2;
    @Getter
    private int capacity; // max number of people that the building can host
    @Getter
    private String description;
    @Getter
    private List<BuildingRating> rating;

    public Building(int id, String address, int hourlyRate, int areaM2, int capacity, String description) {
        this.id = id;
        this.address = address;
        this.hourlyRate = hourlyRate;
        this.areaM2 = areaM2;
        this.capacity = capacity;
        this.description = description;
        this.rating = new ArrayList<>();
    }

    public Building(int id, String address, int hourlyRate, int areaM2, int capacity, String description, List<BuildingRating> rating) {
        this.id = id;
        this.address = address;
        this.hourlyRate = hourlyRate;
        this.areaM2 = areaM2;
        this.capacity = capacity;
        this.description = description;
        this.rating = rating;
    }
}
