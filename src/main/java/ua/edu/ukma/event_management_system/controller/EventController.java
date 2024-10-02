package ua.edu.ukma.event_management_system.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;
import ua.edu.ukma.event_management_system.domain.Event;

import java.util.*;

@RestController
@RequestMapping("event")
public class EventController {

    private ModelMapper modelMapper;
    private EventService eventService;

    @Autowired
    public void setModelWrapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setEventService(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public EventDto getEvent(@PathVariable long id){
        return toDto(eventService.getEventById(id));
    }

    @GetMapping("/")
    public List<EventDto> getEvents(@RequestParam(required = false) String title){
        if(title == null) {
            return eventService.getAllEvents().stream().map(this::toDto).toList();
        }else {
            return eventService.getAllEventsByTitle(title).stream().map(this::toDto).toList();
        }
    }

    @PostMapping("/")
    public void createNewEvent(@RequestBody EventDto eventDto){
        eventService.createEvent(eventDto);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable long id, @RequestBody EventDto eventDto){
        eventService.updateEvent(id, eventDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable long id) {
        eventService.deleteEvent(id);
    }

    private EventDto toDto(Event event){
        return modelMapper.map(event, EventDto.class);
    }
}