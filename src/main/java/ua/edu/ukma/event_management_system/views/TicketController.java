package ua.edu.ukma.event_management_system.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.ukma.event_management_system.domain.Ticket;
import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.domain.UserRole;
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
			case ORGANIZER -> ticketService.getAllTicketsCreatedByUser(user.getId());
			case USER -> ticketService.getAllTicketsForUser(user.getUsername());
		};

		model.addAttribute("tickets", tickets);
		return "tickets/ticket-list";
	}

	@GetMapping("/{id}")
	public String delete(@PathVariable long id) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.getUserByUsername(userDetails.getUsername());

		Ticket ticketToDelete = ticketService.getTicketById(id);

		if (currentUser.getId() == ticketToDelete.getUser().getId() // the deleter is buyer
				|| ticketToDelete.getEvent().getCreator().getId() == currentUser.getId() // the deleter is organizer
				|| currentUser.getUserRole() == UserRole.ADMIN) { //the deleter is admin
			ticketService.removeTicket(id);
		}

		return "redirect:/ticket/";
	}
}
