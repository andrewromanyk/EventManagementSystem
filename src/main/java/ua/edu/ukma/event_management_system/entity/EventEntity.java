package ua.edu.ukma.event_management_system.entity;

import jakarta.persistence.*;
import ua.edu.ukma.event_management_system.domain.Building;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String eventTitle;

    @Column(nullable = false)
    private LocalDateTime dateTimeStart;

    @Column(nullable = false)
    private LocalDateTime dateTimeEnd;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private int numberOfTickets;

    @Column(nullable = false)
    private int minAgeRestriction;

    @ManyToMany
    @JoinTable(name="ticket",
    joinColumns = @JoinColumn(name = "event_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<EventRatingEntity> rating;

    // Default constructor required by JPA
    public EventEntity() {}

    public EventEntity(String eventTitle, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd,
                       BuildingEntity building, String description, int numberOfTickets, int minAgeRestriction) {
        this.eventTitle = eventTitle;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.building = building;
        this.description = description;
        this.numberOfTickets = numberOfTickets;
        this.minAgeRestriction = minAgeRestriction;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public LocalDateTime getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(LocalDateTime dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public int getMinAgeRestriction() {
        return minAgeRestriction;
    }

    public void setMinAgeRestriction(int minAgeRestriction) {
        this.minAgeRestriction = minAgeRestriction;
    }

}
