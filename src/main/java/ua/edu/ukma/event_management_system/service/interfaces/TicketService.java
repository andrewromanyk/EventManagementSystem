package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.Ticket;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.dto.TicketDto;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.entity.EventEntity;
import ua.edu.ukma.event_management_system.entity.TicketEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;

import java.util.List;

public interface TicketService {
	Ticket createTicket(TicketDto ticket);

	Ticket purchaseTicket(UserDto user, EventDto event, int price);

	List<Ticket> getAllTickets();

	Ticket getTicketById(long ticketId);

	void updateTicket(long id, TicketDto updatedTicket);

	void removeTicket(long ticketId);


}
