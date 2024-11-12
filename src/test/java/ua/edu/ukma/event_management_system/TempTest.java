package ua.edu.ukma.event_management_system;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.edu.ukma.event_management_system.domain.Ticket;
import ua.edu.ukma.event_management_system.domain.UserRole;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;
import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.entity.TicketEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Configurator.class})
@Import({ModelMapper.class})
public class TempTest {
    @Autowired
    private ModelMapper modelMapper;

    @Test
    void test(){
        UserEntity user1 = new UserEntity(UserRole.USER, "and123", "Andriy",
                "Petrenko", "andriisuper@gmail.com",
                "$2a$10$JFbuJfw3FZSs.9rDOO3jiOP2r9HjnJczXsWiqvEhkCyyWVHNjuQyy", // Andri1Sup3r
                "380999777444", 30);
        UserEntity user2 = new UserEntity(UserRole.ORGANIZER, "maria_shevchenko", "Maria",
                "Shevchenko", "shevchenkoM@gmail.com",
                "$2a$10$lhATh/lhdonUDlGJrhZtiOU0U8AB18UvdIeTr2mTAMObZZ3G4e2Ui", // maria1234
                "380111486754", 24);
        UserEntity admin = new UserEntity(UserRole.ADMIN, "admin", "Admin",
                "eqwe", "emsAdmin@gmail.com",
                "$2a$10$NY5RpFbjoy4TnN.DPH/OEOhnq0.2/sNcfwbJHLW5YTfW.wOugwTsS", // admin123
                "380777777777", 35);

        BuildingEntity building1 = new BuildingEntity("123 Main St", 100, 300,
                500, "Conference Hall");
        BuildingEntity building2 = new BuildingEntity("654 Central St", 500, 200,
                100, "Concert Hall");

        EventEntity event1 = new EventEntity("queen-concert", LocalDateTime.now().plusDays(10),
                LocalDateTime.now().plusDays(10).plusHours(2),
                building2, "A great queen concert", 100, 18);

        EventEntity event2 = new EventEntity("it-conference", LocalDateTime.now().plusDays(3),
                LocalDateTime.now().plusDays(3).plusHours(3),
                building1, "IT conference discussing Django vs SpringBoot",
                50, 25);

        EventEntity eventZeroTickets = new EventEntity("zero tickets",
                LocalDateTime.now().plusDays(3),
                LocalDateTime.now().plusDays(3).plusHours(3),
                building2, "zero tickets event",
                0, 1);

        TicketEntity ticket1 = new TicketEntity(user1, event1, 100, LocalDateTime.now());
        TicketEntity ticket2 = new TicketEntity(user1, event2, 200, LocalDateTime.now());

        System.out.println(modelMapper.map(ticket1, Ticket.class));
    }
}
