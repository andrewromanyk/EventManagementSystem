package ua.edu.ukma.event_management_system.controller.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("event")
public class EventController {

	private EventService eventService;
	private BuildingService buildingService;

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("/")
	public String get(Model model) {
		List<Event> events = eventService.getAllEvents();

		// Prepare a map of event IDs to Base64-encoded images
		Map<Integer, String> imageMap = new HashMap<>();
		for (Event event : events) {
			if (event.getImage() != null) {
				String base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(event.getImage());
				imageMap.put(event.getId(), base64Image);
			}
		}

		model.addAttribute("events", events); // Add events as usual
		model.addAttribute("imageMap", imageMap); // Add the image map
		return "events/events";
	}

	@GetMapping("/create")
	public String showCreateEventForm(Model model) {
		//add building attribute here to track in the create-form
		model.addAttribute("event", new Event());
		return "events/event-form";
	}

	@PostMapping("/create")
	public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("event", eventDto);
			return "events/event-form";
		}
		eventService.createEvent(eventDto);
		return "redirect:/event";
	}
}
