package ua.edu.ukma.event_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.ukma.event_management_system.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    EventEntity findByEventTitle(String eventTitle);
}