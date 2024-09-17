package ua.edu.ukma.event_management_system.entity;

public class Building {
    private int id;
    private String address;
    private int hourlyRate;
    private int areaM2;
    private int capacity; // max number of people that the building can host
    private String description;

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
}
