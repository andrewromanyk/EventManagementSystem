package ua.edu.ukma.event_management_system.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.ukma.event_management_system.domain.Ticket;
import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.service.interfaces.TicketService;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

import java.util.List;

@Controller
@RequestMapping("ticket")
public class TicketController {

	private TicketService ticketService;
	private UserService userService;

	@Autowired
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String getAll(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUsername(userDetails.getUsername());
		List<Ticket> tickets = switch (user.getUserRole()) {
			case ADMIN -> ticketService.getAllTickets();
			case USER, ORGANIZER -> ticketService.getAllTicketsForUser(user.getUsername());
		};

		model.addAttribute("tickets", tickets);
		return "tickets/ticket-list";
	}
}
