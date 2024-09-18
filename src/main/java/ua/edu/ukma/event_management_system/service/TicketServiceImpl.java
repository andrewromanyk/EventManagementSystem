package ua.edu.ukma.event_management_system.service;

import ua.edu.ukma.event_management_system.entity.Ticket;
import ua.edu.ukma.event_management_system.entity.User;
import ua.edu.ukma.event_management_system.service.interfaces.TicketService;

public class TicketServiceImpl implements TicketService {

    @Override
    public void registerTicket() {
        System.out.println("Created a ticket!");
    }

    @Override
    public Ticket purchaseTicket(int eventId, User user) {
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
