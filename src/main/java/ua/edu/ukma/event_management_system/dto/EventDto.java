package ua.edu.ukma.event_management_system.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EventDto {
    private int id;
    private String eventTitle;
    private LocalDateTime dateTimeStart;
    private LocalDateTime dateTimeEnd;
    private BuildingDto building;
    private String description;
    private int numberOfTickets;
    private int minAgeRestriction;
    private List<EventRatingDto> rating;
    private List<UserDto> users;

    public EventDto(int id, String eventTitle, LocalDateTime dateTimeStart,
                    LocalDateTime dateTimeEnd, BuildingDto building, String description,
                    int numberOfTickets, int minAgeRestriction, List<UserDto> users) {
        this.id = id;
        this.eventTitle = eventTitle;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.building = building;
        this.description = description;
        this.numberOfTickets = numberOfTickets;
        this.minAgeRestriction = minAgeRestriction;
        this.rating = new ArrayList<>();
        this.users = users;
    }

    public EventDto(int id, String eventTitle, LocalDateTime dateTimeStart,
                    LocalDateTime dateTimeEnd, BuildingDto building, String description,
                    int numberOfTickets, int minAgeRestriction, List<UserDto> users,
                    List<EventRatingDto> rating){
        this(id, eventTitle, dateTimeStart, dateTimeEnd, building, description,
                numberOfTickets, minAgeRestriction, users);
        this.rating = rating;
    }
}
