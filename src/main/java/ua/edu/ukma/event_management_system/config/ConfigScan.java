package ua.edu.ukma.event_management_system.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ua.edu.ukma")
@EnableAspectJAutoProxy
public class ConfigScan {
}
