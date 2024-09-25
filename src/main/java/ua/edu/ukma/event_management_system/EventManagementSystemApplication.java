package ua.edu.ukma.event_management_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.edu.ukma.event_management_system.entity.TicketEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.service.DatabasePopulatorService;

import java.util.Optional;

@SpringBootApplication
public class EventManagementSystemApplication implements CommandLineRunner {

	private final DatabasePopulatorService databasePopulatorService;

	public EventManagementSystemApplication(DatabasePopulatorService databasePopulatorService) {
		this.databasePopulatorService = databasePopulatorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(EventManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		databasePopulatorService.populateDatabase();
		System.out.println("Database populated with test data successfully!");
	}
}
