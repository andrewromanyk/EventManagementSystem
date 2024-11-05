package ua.edu.ukma.event_management_system;

import org.modelmapper.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.domain.Ticket;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.dto.TicketDto;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;
import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.service.BuildingServiceImpl;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;

@Configuration
public class Configurator {

    @Bean
    @ConditionalOnSingleCandidate(BuildingService.class)
    public BuildingService buildingService() {
        return new BuildingServiceImpl();
    }

    @Primary
    @Bean
    //@ConditionalOnMissingBean(ModelMapper.class)
    public ModelMapper modelMapper() {
        ModelMapper mapperResult = new ModelMapper();

        TypeMap<Event, EventDto> eventMapper = mapperResult.createTypeMap(Event.class, EventDto.class);
        eventMapper.addMappings(mapper -> mapper.map(src -> src.getBuilding().getId(), EventDto::setBuilding));

        System.out.println("Created overridden ModelMapper");

        Converter<Long, BuildingEntity> toBuildingConverter = ctx -> mapperResult.map(buildingService().getBuildingById(ctx.getSource()), BuildingEntity.class);
        TypeMap<EventDto, EventEntity> eventMapperRev = mapperResult.createTypeMap(EventDto.class, EventEntity.class);
        eventMapperRev.addMappings(mapper -> mapper.using(toBuildingConverter).map(EventDto::getBuilding, EventEntity::setBuilding));

        return mapperResult;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
