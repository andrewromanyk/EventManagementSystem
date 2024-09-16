package ua.edu.ukma.event_management_system.dao;

import org.springframework.stereotype.Component;
import ua.edu.ukma.event_management_system.dao.interfaces.TicketDao;
import ua.edu.ukma.event_management_system.entity.Ticket;

import java.util.List;
import java.util.Optional;

@Component
public class TicketDaoImpl implements TicketDao {
	@Override
	public List<Ticket> findAll() {
		return null;
	}

	@Override
	public Optional<Ticket> findById(int id) {
		return Optional.empty();
	}

	@Override
	public void save(Ticket ticket) {

	}

	@Override
	public void deleteById(int id) {

	}

	@Override
	public void delete(Ticket ticket) {

	}
}
