package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.entity.EventRatingEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.repository.EventRepository;

public interface EventService {

    EventEntity createEvent(EventEntity event);

    EventEntity getEventById(long eventId);

    void updateEvent(EventEntity event);

    void deleteEvent(long eventId);

    EventRatingEntity rateEvent(EventEntity event, byte rating, UserEntity author, String comment);
}
