package ua.edu.ukma.event_management_system.dto;

import lombok.*;

import java.time.LocalDateTime;
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
//    private List<EventRatingDto> rating;

    public EventDto(int id, String eventTitle, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd,
                    BuildingDto building, String description,
                    int numberOfTickets, int minAgeRestriction){
        this.id = id;
        this.eventTitle = eventTitle;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
        this.building = building;
        this.description = description;
        this.numberOfTickets = numberOfTickets;
        this.minAgeRestriction = minAgeRestriction;
    }

//    public EventDto(int id, String eventTitle, LocalDateTime dateTimeStart,
//                    LocalDateTime dateTimeEnd, BuildingDto building, String description,
//                    int numberOfTickets, int minAgeRestriction, List<EventRatingDto> rating){
//        this(id, eventTitle, dateTimeStart, dateTimeEnd, building, description, numberOfTickets, minAgeRestriction);
//        this.rating = rating;
//    }
}
