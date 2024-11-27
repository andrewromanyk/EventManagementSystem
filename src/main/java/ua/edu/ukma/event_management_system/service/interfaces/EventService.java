package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.entity.EventRatingEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.repository.EventRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    Event createEvent(EventDto event);

    Event getEventById(long eventId);

    List<Event> getAllEventsByTitle(String title);

    void updateEvent(Long id, EventDto updatedEvent);

    void deleteEvent(long eventId);

    List<Event>getAllRelevant();

    List<Event> getAllForOrganizer(Long organizerId);

    List<Event> getAllThatIntersect(LocalDateTime Start, LocalDateTime End, long id);
//    EventRatingEntity rateEvent(EventDto event, byte rating, UserDto author, String comment);
}
