package ua.edu.ukma.event_management_system.service;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.domain.UserRole;
import ua.edu.ukma.event_management_system.entity.*;
import ua.edu.ukma.event_management_system.repository.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
@Transactional
public class DatabasePopulatorService {

    private final BuildingRepository buildingRepository;
    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final EventRatingRepository eventRatingRepository;
    private final BuildingRatingRepository buildingRatingRepository;

    public DatabasePopulatorService(BuildingRepository br, EventRepository er,
                                    TicketRepository tr, UserRepository ur,
                                    EventRatingRepository err, BuildingRatingRepository brr) {
        this.buildingRepository = br;
        this.eventRepository = er;
        this.ticketRepository = tr;
        this.userRepository = ur;
        this.eventRatingRepository = err;
        this.buildingRatingRepository = brr;
    }

    public void populateDatabase() throws IOException {
        UserEntity user1 = new UserEntity(UserRole.USER, "and123", "Andriy",
                "Petrenko", "andriisuper@gmail.com",
                "$2a$10$JFbuJfw3FZSs.9rDOO3jiOP2r9HjnJczXsWiqvEhkCyyWVHNjuQyy", // Andri1Sup3r
                "380999777444", LocalDateTime.of(2005, 01, 01, 0, 1));
        UserEntity user2 = new UserEntity(UserRole.ORGANIZER, "maria_shevchenko", "Maria",
                "Shevchenko", "shevchenkoM@gmail.com",
                "$2a$10$lhATh/lhdonUDlGJrhZtiOU0U8AB18UvdIeTr2mTAMObZZ3G4e2Ui", // maria1234
                "380111486754", LocalDateTime.of(2010, 01, 01, 0, 1));
        UserEntity admin = new UserEntity(UserRole.ADMIN, "admin", "Admin",
                "eqwe", "emsAdmin@gmail.com",
                "$2a$10$NY5RpFbjoy4TnN.DPH/OEOhnq0.2/sNcfwbJHLW5YTfW.wOugwTsS", // admin123
                "380777777777", LocalDateTime.of(1995, 01, 01, 0, 1));
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(admin);

        BuildingEntity building1 = new BuildingEntity("123 Main St", 100, 300,
                500, "Conference Hall");
        BuildingEntity building2 = new BuildingEntity("654 Central St", 500, 200,
                100, "Concert Hall");
        buildingRepository.save(building1);
        buildingRepository.save(building2);

        byte[] image = Files.readAllBytes(Path.of("src/main/resources/stock_photo.jpg"));

        EventEntity event1 = new EventEntity("queen-concert", LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(10).plusHours(2),
                building2, "A great queen concert", 100, 18, image, admin, 100);

        EventEntity event2 = new EventEntity("it-conference", LocalDateTime.now().plusHours(3),
                LocalDateTime.now().plusHours(6),
                building1, "IT conference discussing Django vs SpringBoot",
                50, 25, image, user2, 150);

        EventEntity eventZeroTickets = new EventEntity("High school reunion",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(3),
                building2, "zero tickets event",
                0, 1, image, user2, 200);
        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(eventZeroTickets);

        TicketEntity ticket1 = new TicketEntity(user1, event1, 100, LocalDateTime.now());
        TicketEntity ticket2 = new TicketEntity(user1, event2, 200, LocalDateTime.now());
        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);

//        EventRatingEntity rating1 = new EventRatingEntity(event1, (byte) 4, user1, "");
//        eventRatingRepository.save(rating1);
//
//        BuildingRatingEntity rating2 = new BuildingRatingEntity(building1, (byte) 5, user2, "");
//        buildingRatingRepository.save(rating2);
    }
}