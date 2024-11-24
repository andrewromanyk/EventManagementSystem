package ua.edu.ukma.event_management_system.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return "events";
	}
}
