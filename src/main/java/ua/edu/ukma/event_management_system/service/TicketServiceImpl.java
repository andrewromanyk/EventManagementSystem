package ua.edu.ukma.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.edu.ukma.event_management_system.entity.Ticket;
import ua.edu.ukma.event_management_system.entity.User;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;
import ua.edu.ukma.event_management_system.service.interfaces.TicketService;

@Component
public class TicketServiceImpl implements TicketService {

    private final EventService eventService;

    @Autowired
    public TicketServiceImpl(EventService eventService) {  //DI with constructor
        this.eventService = eventService;

    }
    @Override
    public Ticket purchaseTicket(int eventId, User user) {
        //logic of buying ticket
        return null;
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        return null;
    }

    @Override
    public void updateTicket(Ticket updatedTicket) {

    }

    @Override
    public void returnTicket(int ticketId) {

    }
}
