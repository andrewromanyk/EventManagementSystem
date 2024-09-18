package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.entity.Ticket;
import ua.edu.ukma.event_management_system.entity.User;

public interface TicketService {

	Ticket purchaseTicket(int eventId, User user);

	Ticket getTicketById(int ticketId);

	void updateTicket(Ticket updatedTicket);

	void returnTicket(int ticketId);


}
