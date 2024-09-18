package ua.edu.ukma.event_management_system.service;

import ua.edu.ukma.event_management_system.service.interfaces.TicketService;

public class TicketServiceImpl implements TicketService {

    @Override
    public void registerTicket() {
        System.out.println("Create a ticket!");
    }
}
