package ua.edu.ukma.event_management_system;

import org.modelmapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.BuildingRating;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.domain.Ticket;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.dto.TicketDto;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;
import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.service.BuildingServiceImpl;
import ua.edu.ukma.event_management_system.service.EventServiceImpl;
import ua.edu.ukma.event_management_system.service.TicketServiceImpl;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;
import ua.edu.ukma.event_management_system.service.interfaces.TicketService;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Configuration
public class Configurator {

    private final static Logger logger = LoggerFactory.getLogger(Configurator.class);

    @Bean
    @ConditionalOnSingleCandidate(BuildingService.class)
    public BuildingService buildingService() {
        return new BuildingServiceImpl();
    }

    @Bean
    @ConditionalOnSingleCandidate(EventService.class)
    public EventService eventService() {
        return new EventServiceImpl();
    }

//    @Bean
//    @ConditionalOnSingleCandidate(TicketService.class)
//    public TicketService ticketService() {
//        return new TicketServiceImpl();
//    }

    @Primary
    @Bean
    //@ConditionalOnMissingBean(ModelMapper.class)
    public ModelMapper modelMapper() {
        ModelMapper mapperResult = new ModelMapper();

        // Event
        // domain to dto
        TypeMap<Event, EventDto> eventMapper = mapperResult.createTypeMap(Event.class, EventDto.class);
        eventMapper.addMappings(mapper -> mapper.map(src -> src.getBuilding().getId(), EventDto::setBuilding));

//        System.out.println("Created overridden ModelMapper");
        logger.info("Created 1 part modelmapper");

        // dto to entity
        Converter<Long, BuildingEntity> toBuildingConverter = ctx -> mapperResult.map(buildingService().getBuildingById(ctx.getSource()), BuildingEntity.class);
        TypeMap<EventDto, EventEntity> eventMapperRev = mapperResult.createTypeMap(EventDto.class, EventEntity.class);
        eventMapperRev.addMappings(mapper -> mapper.using(toBuildingConverter).map(EventDto::getBuilding, EventEntity::setBuilding));

        logger.info("Created 2 part modelmapper");
        // Building
        // domain to dto
        TypeMap<Building, BuildingDto> buildingMapper = mapperResult.createTypeMap(Building.class, BuildingDto.class);
        buildingMapper.addMappings(mapper ->
                mapper.map(src -> src.getRating() == null
                                ? new ArrayList<>() // Handle null safely
                                : src.getRating().stream()
                                .map(BuildingRating::getId)
                                .collect(Collectors.toList()), // Use Collectors.toList() for Java 8+
                        BuildingDto::setRating)
        );
        logger.info("Created 3 part modelmapper");

        // dto to model
        Converter<Long, BuildingRating> toRatingConverter = ctx -> mapperResult.map(buildingService().getRatingById(ctx.getSource()), BuildingRating.class);
        TypeMap<BuildingDto, Building> buildingDtoToDomain = mapperResult.createTypeMap(BuildingDto.class, Building.class);
        buildingDtoToDomain.addMappings(mapper -> mapper.using(toRatingConverter).map(BuildingDto::getRating, Building::setRating));
        logger.info("Created 4 part modelmapper");

        // Ticket
        // domain to dto
        TypeMap<Ticket, TicketDto> ticketToDto = mapperResult.createTypeMap(Ticket.class, TicketDto.class);
        ticketToDto.addMappings(mapper -> mapper.map(src -> src.getEvent().getId(), TicketDto::setEvent));
        logger.info("Created 5 part modelmapper");

        // dto to domain
        Converter<Integer, Event> idToEvent = ctx -> mapperResult.map(eventService().getEventById(ctx.getSource()), Event.class);
        TypeMap<TicketDto, Ticket> ticketDtoToDomain = mapperResult.createTypeMap(TicketDto.class, Ticket.class);
        ticketDtoToDomain.addMappings(mapper -> mapper.using(idToEvent).map(TicketDto::getEvent, Ticket::setEvent));
        logger.info("Created 6 part modelmapper");

        return mapperResult;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
