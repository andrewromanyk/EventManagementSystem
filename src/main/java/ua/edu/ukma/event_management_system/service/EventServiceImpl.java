package ua.edu.ukma.event_management_system.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;
import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.exceptions.handler.ContentValidator;
import ua.edu.ukma.event_management_system.repository.BuildingRepository;
import ua.edu.ukma.event_management_system.repository.EventRatingRepository;
import ua.edu.ukma.event_management_system.repository.EventRepository;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final BuildingRepository buildingRepository;
    private final BuildingService buildingService;
    private final EventRatingRepository eventRatingRepository;

    @Autowired
    public EventServiceImpl(ModelMapper modelMapper, BuildingRepository buildingRepository, EventRepository eventRepository,
                            BuildingService buildingService, EventRatingRepository eventRatingRepository) {
        this.buildingRepository = buildingRepository;
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
        this.buildingService = buildingService;
        this.eventRatingRepository = eventRatingRepository;
    }

    @Override
    public List<Event> getAllEvents(){
        return eventRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Event getEventById(long eventId) {
        return toDomain(eventRepository.findById(eventId).orElseThrow());
    }

    @Override
    public List<Event> getAllEventsByTitle(String title) {
        return eventRepository.findAllByEventTitle(title).stream().map(this::toDomain).toList();
    }

    @Override
    public Event createEvent(EventDto event) {
        // Ensures the building is saved before saving the event
        EventEntity toSave = toEntity(event);
        BuildingEntity buildingEntity = toSave.getBuilding();
        if (buildingEntity != null) {
            Optional<BuildingEntity> existingBuilding = buildingRepository.findById(buildingEntity.getId());
            if (!existingBuilding.isPresent())
                buildingEntity = buildingRepository.save(buildingEntity);
            else
                buildingEntity = existingBuilding.get();
        }
        return toDomain(eventRepository.save(toSave));
    }

    @Override
    public void updateEvent(Long id, EventDto updatedEvent) {
        Optional<EventEntity> existingEventOpt = eventRepository.findById(id);

        if (existingEventOpt.isPresent()) {
            EventEntity existingEvent = existingEventOpt.get();
            ContentValidator.validateContent(updatedEvent.getEventTitle());
            existingEvent.setEventTitle(updatedEvent.getEventTitle());
            existingEvent.setDateTimeStart(updatedEvent.getDateTimeStart());
            existingEvent.setDateTimeEnd(updatedEvent.getDateTimeEnd());

            BuildingEntity buildingEntity = buildingRepository.findById(updatedEvent.getBuilding()).get();
            if(buildingEntity != null){
                Optional<BuildingEntity> existingBuilding = buildingRepository.findById(buildingEntity.getId());
                if (!existingBuilding.isPresent()){
                    buildingEntity = buildingRepository.save(buildingEntity);
                }else{
                    buildingEntity = existingBuilding.get();
                }
            }

            existingEvent.setBuilding(buildingEntity);
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

    private Event toDomain(EventEntity event){
        return modelMapper.map(event, Event.class);
    }

    private EventEntity toEntity(EventDto event){
        return modelMapper.map(event, EventEntity.class);
    }

    private BuildingEntity toEntity(BuildingDto building){
        return modelMapper.map(building, BuildingEntity.class);
    }

    private UserEntity toEntity(UserDto user){
        return modelMapper.map(user, UserEntity.class);
    }

    private EventDto toDto(EventEntity event){
        return modelMapper.map(event, EventDto.class);
    }


//    @Override
//    public EventRatingEntity rateEvent(EventDto event, byte rating, UserDto author, String comment) {
//        return eventRatingRepository.save(new EventRatingEntity(toEntity(event), rating, toEntity(author), comment));
//    }
}
