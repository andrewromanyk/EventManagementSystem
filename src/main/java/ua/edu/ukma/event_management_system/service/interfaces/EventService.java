package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.entity.EventEntity;

public interface EventService {

    EventEntity createEvent(EventEntity event);

    EventEntity getEventById(long eventId);

    void updateEvent(EventEntity event);

    void deleteEvent(long eventId);
}
