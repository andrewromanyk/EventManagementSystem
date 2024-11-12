package ua.edu.ukma.event_management_system.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;
import ua.edu.ukma.event_management_system.domain.Building;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
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

    @OneToMany(/*fetch = FetchType.LAZY,*/ mappedBy = "event")
    private List<EventRatingEntity> rating;


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
}