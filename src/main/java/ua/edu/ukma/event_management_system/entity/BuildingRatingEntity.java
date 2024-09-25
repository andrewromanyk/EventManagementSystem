package ua.edu.ukma.event_management_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "building_rating")
public class BuildingRatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;

    @Column(nullable = false)
    private byte rating;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @Column(nullable = false)
    private String comment;

}