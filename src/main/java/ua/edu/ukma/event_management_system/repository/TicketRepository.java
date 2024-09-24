package ua.edu.ukma.event_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.ukma.event_management_system.entity.TicketEntity;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
    TicketEntity findByPrice(int price);
}