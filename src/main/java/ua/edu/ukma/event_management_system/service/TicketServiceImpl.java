package ua.edu.ukma.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.event_management_system.dao.interfaces.TicketDao;
import ua.edu.ukma.event_management_system.service.interfaces.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	private TicketDao ticketDao;

	public TicketServiceImpl(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	@Autowired
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
}
