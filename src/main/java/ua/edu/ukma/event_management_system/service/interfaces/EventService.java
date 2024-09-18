package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.entity.Event;

public interface EventService {

    void createEvent(Event event);

    Event getEventById(int eventId);

    void updateEvent(Event event);

    void deleteEvent(int eventId);
}
