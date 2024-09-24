package ua.edu.ukma.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;

@Component
public class EventServiceImpl implements EventService {


    private BuildingService buildingService;

    @Autowired
    public void setBuildingService(BuildingService buildingService) {  //DI with setter
        this.buildingService = buildingService;
    }

    @Override
    public void createEvent(Event event) {
        System.out.println("Created event!");
    }

    @Override
    public Event getEventById(int eventId) {
        return null;
    }

    @Override
    public void updateEvent(Event event) {

    }

    @Override
    public void deleteEvent(int eventId) {

    }
}
