package ua.edu.ukma.event_management_system;

import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;
import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.service.BuildingServiceImpl;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;

@Configuration
@ComponentScan("ua.edu.ukma.event_management_system")
public class Configurator {

    @Bean
    public BuildingService buildingService() {
        return new BuildingServiceImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapperResult = new ModelMapper();

        TypeMap<Event, EventDto> eventMapper = mapperResult.createTypeMap(Event.class, EventDto.class);
        eventMapper.addMappings(mapper -> mapper.map(src -> src.getBuilding().getId(), EventDto::setBuilding));

        Converter<Long, BuildingEntity> toBuildingConverter = ctx -> mapperResult.map(buildingService().getBuildingById(ctx.getSource()), BuildingEntity.class);
        TypeMap<EventDto, EventEntity> eventMapperRev = mapperResult.createTypeMap(EventDto.class, EventEntity.class);
        eventMapperRev.addMappings(mapper -> mapper.using(toBuildingConverter).map(EventDto::getBuilding, EventEntity::setBuilding));

        return mapperResult;
    }
}
