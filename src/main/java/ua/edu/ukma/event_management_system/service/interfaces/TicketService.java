package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.entity.TicketEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;

import java.util.List;

public interface TicketService {
	TicketEntity createTicket(TicketEntity ticket);

	TicketEntity purchaseTicket(UserEntity user, EventEntity event, int price);

	List<TicketEntity> getAllTickets();

	TicketEntity getTicketById(long ticketId);

	void updateTicket(TicketEntity updatedTicket);

	void removeTicket(long ticketId);


}
