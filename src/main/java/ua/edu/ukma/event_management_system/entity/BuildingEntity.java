package ua.edu.ukma.event_management_system.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "building")
public class BuildingEntity {
    @Id
    // default strategy, JPA automatically selects the appropriate
    // generation strategy based on the database used
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int hourlyRate;

    @Column(nullable = false)
    private int areaM2;

    @Column(nullable = false)
    private int capacity;

    @Column(length = 500)
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private List<BuildingRatingEntity> rating;

    // Default constructor for JPA
    public BuildingEntity(){}

    public BuildingEntity(String address, int hourlyRate, int areaM2, int capacity, String description) {
        this.address = address;
        this.hourlyRate = hourlyRate;
        this.areaM2 = areaM2;
        this.capacity = capacity;
        this.description = description;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getAreaM2() {
        return areaM2;
    }

    public void setAreaM2(int areaM2) {
        this.areaM2 = areaM2;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
