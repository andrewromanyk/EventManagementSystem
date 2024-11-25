package ua.edu.ukma.event_management_system.views;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
	public String get(Model model) throws IOException {
		List<Event> events = eventService.getAllEvents();

		// Prepare a map of event IDs to Base64-encoded images
		Map<Integer, String> imageMap = new HashMap<>();
		for (Event event : events) {
			if (event.getImage() != null) {
				String base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(event.getImage());
				imageMap.put(event.getId(), base64Image);
			}
			else {
				String base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(Files.readAllBytes(Path.of("src/main/resources/stock_photo.jpg")));
				imageMap.put(event.getId(), base64Image);
			}
		}

		model.addAttribute("events", events); // Add events as usual
		model.addAttribute("imageMap", imageMap); // Add the image map
		return "events/events";
	}

	@GetMapping("/{id}")
	public String get(@PathVariable long id, Model model) throws IOException {
		Event event = eventService.getEventById(id);

		// Prepare a map of event IDs to Base64-encoded images
		String base64Image;
		if (event.getImage() != null) {
			base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(event.getImage());
		}
		else {
			base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(Files.readAllBytes(Path.of("src/main/resources/stock_photo.jpg")));
		}

		model.addAttribute("event", event); // Add events as usual
		model.addAttribute("imageMap", base64Image); // Add the image map
		return "events/event";
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
		return "redirect:/events";
	}
}
