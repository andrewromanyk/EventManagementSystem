package ua.edu.ukma.event_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.edu.ukma.event_management_system.entity.TicketEntity;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    TicketEntity findByPrice(int price);
    List<TicketEntity> findAllByUserId(int userId);
    List<TicketEntity> findAllByUserUsername(String username);
    @Query("SELECT t.id FROM TicketEntity t WHERE DATEDIFF(DAY, now(), t.purchaseDate) <= 1")
    List<Long> findAllCreatedToday();
}

