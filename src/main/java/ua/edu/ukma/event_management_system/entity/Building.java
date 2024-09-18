package ua.edu.ukma.event_management_system.entity;

import ua.edu.ukma.event_management_system.service.interfaces.Ratable;

public class Building implements Ratable {
    private int id;
    private String address;
    private int hourlyRate;
    private int areaM2;
    private int capacity; // max number of people that the building can host
    private String description;
    private int rating;

    public Building(int id, String address, int hourlyRate, int areaM2, int capacity, String description) {
        this.id = id;
        this.address = address;
        this.hourlyRate = hourlyRate;
        this.areaM2 = areaM2;
        this.capacity = capacity;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public int getAreaM2() {
        return areaM2;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void rate(int rating) {
        if (rating < 1)
            this.rating = 1;
        else if(rating > 10)
            this.rating = 10;
        else
            this.rating = rating;
    }

    @Override
    public int getRating() {
        return rating;
    }
}
