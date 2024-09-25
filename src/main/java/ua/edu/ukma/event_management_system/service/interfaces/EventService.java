package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.Event;

public interface EventService {

    void createEvent(Event event);

    Event getEventById(long eventId);

    void updateEvent(Event event);

    void deleteEvent(long eventId);
}
