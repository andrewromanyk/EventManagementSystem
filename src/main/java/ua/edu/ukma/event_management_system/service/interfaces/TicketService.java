package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.Ticket;
import ua.edu.ukma.event_management_system.domain.User;

public interface TicketService {
	void registerTicket();

	Ticket purchaseTicket(int eventId, User user);

	Ticket getTicketById(int ticketId);

	void updateTicket(Ticket updatedTicket);

	void returnTicket(int ticketId);


}
