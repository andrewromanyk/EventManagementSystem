package ua.edu.ukma.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;
import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.repository.BuildingRepository;
import ua.edu.ukma.event_management_system.repository.EventRepository;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;

import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final BuildingService buildingService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, BuildingService buildingService) {
        this.eventRepository = eventRepository;
        this.buildingService = buildingService;
    }


    @Override
    public EventEntity createEvent(EventEntity event) {
        return eventRepository.save(event);
    }

    @Override
    public EventEntity getEventById(long eventId) {
        EventEntity event = eventRepository.findById(eventId).get();
        return event;
    }

    public void updateEvent(EventEntity updatedEvent) {
        Optional<EventEntity> existingEventOpt = eventRepository.findById(updatedEvent.getId());
        if (existingEventOpt.isPresent()) {
            EventEntity existingEvent = existingEventOpt.get();
            existingEvent.setEventTitle(updatedEvent.getEventTitle());
            existingEvent.setDateTimeStart(updatedEvent.getDateTimeStart());
            existingEvent.setDateTimeEnd(updatedEvent.getDateTimeEnd());
            existingEvent.setBuilding(updatedEvent.getBuilding());
            existingEvent.setDescription(updatedEvent.getDescription());
            existingEvent.setNumberOfTickets(updatedEvent.getNumberOfTickets());
            existingEvent.setMinAgeRestriction(updatedEvent.getMinAgeRestriction());
            eventRepository.save(existingEvent);
        }
    }

    @Override
    public void deleteEvent(long eventId) {
        eventRepository.deleteById(eventId);
    }
}
