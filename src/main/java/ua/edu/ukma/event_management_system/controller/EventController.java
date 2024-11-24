package ua.edu.ukma.event_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;

@Controller
@RequestMapping("event")
public class EventController {

	private EventService eventService;

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/")
	public String get(Model model) {
		model.addAttribute("events", eventService.getAllEvents());
		return "events";
	}
}
