package ua.edu.ukma.event_management_system.dao.interfaces;

import ua.edu.ukma.event_management_system.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketDao {
    List<Ticket> findAll();

    Optional<Ticket> findById(int id);
    void save(Ticket ticket);

    void deleteById(int id);

    void delete(Ticket ticket);
}
