package ua.edu.ukma.event_management_system;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.edu.ukma.event_management_system.service.BuildingServiceImpl;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;

@Configuration
@ComponentScan("ua.edu.ukma.event_management_system")
public class Configurator {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public BuildingService buildingService() {
        return new BuildingServiceImpl();
    }
}
