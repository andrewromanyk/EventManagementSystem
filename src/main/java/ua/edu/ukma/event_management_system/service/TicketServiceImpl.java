package ua.edu.ukma.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.entity.TicketEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.repository.TicketRepository;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;
import ua.edu.ukma.event_management_system.service.interfaces.TicketService;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final EventService eventService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(EventService eventService) {  //DI with constructor
        this.eventService = eventService;
    }

    @Override
    public TicketEntity createTicket(TicketEntity ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public TicketEntity purchaseTicket(UserEntity user, EventEntity event, int price) {
        TicketEntity ticket = new TicketEntity(user, event, price);
        return ticketRepository.save(ticket);
    }

    @Override
    public List<TicketEntity> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public TicketEntity getTicketById(long ticketId) {
        return ticketRepository.findById(ticketId).orElse(null);
    }

    @Override
    public void updateTicket(TicketEntity updatedTicket) {
        Optional<TicketEntity> existingUserOpt = ticketRepository.findById(updatedTicket.getId());
        if (existingUserOpt.isPresent()) {
            TicketEntity existingTicket = existingUserOpt.get();
            updatedTicket.setEvent(updatedTicket.getEvent());
            updatedTicket.setPrice(updatedTicket.getPrice());
            updatedTicket.setUser(updatedTicket.getUser());
            ticketRepository.save(existingTicket);
        }
    }

    @Override
    public void removeTicket(long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
