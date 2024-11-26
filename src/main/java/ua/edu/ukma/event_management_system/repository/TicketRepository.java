package ua.edu.ukma.event_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.ukma.event_management_system.dto.TicketDto;
import ua.edu.ukma.event_management_system.entity.TicketEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    TicketEntity findByPrice(int price);
    List<TicketEntity> findAllByUserId(int userId);
    List<TicketEntity> findAllByUserUsername(String username);
    @Query("SELECT t.id FROM TicketEntity t WHERE DATEDIFF(DAY, now(), t.purchaseDate) <= 1")
    List<Long> findAllCreatedToday();
    List<TicketEntity> findTicketEntitiesByEvent_Creator_Id(long creator);

//    @Modifying
//    @Query(value = "INSERT INTO ticket (user_id, event_id, price, purchase_date) VALUES (?, ?, ?, ?)", nativeQuery = true)
//    @Transactional
//    TicketEntity saveTicketDto(long userId, long eventId, double price, LocalDateTime purchaseDate);
}

