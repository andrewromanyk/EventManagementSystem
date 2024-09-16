package ua.edu.ukma.event_management_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.edu.ukma.event_management_system.dao.UserDaoImpl;
import ua.edu.ukma.event_management_system.dao.interfaces.UserDao;

@Configuration
@ComponentScan("ua.edu.ukma.event_management_system")
public class BeanConfig {

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl();
    }
}
