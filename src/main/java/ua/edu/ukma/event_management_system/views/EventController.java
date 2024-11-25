package ua.edu.ukma.event_management_system.views;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

		model.addAttribute("events", events);
		model.addAttribute("imageMap", imageMap);
		return "events/events";
	}

	@GetMapping("/{id}")
	public String get(@PathVariable long id, Model model) throws IOException {
		Event event = eventService.getEventById(id);

		String base64Image;
		if (event.getImage() != null) {
			base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(event.getImage());
		}
		else {
			base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(Files.readAllBytes(Path.of("src/main/resources/stock_photo.jpg")));
		}

		model.addAttribute("event", event);
		model.addAttribute("img", base64Image);
		return "events/event";
	}

	@GetMapping("/create")
	public String showCreateEventForm(Model model) {
		//add building attribute here to track in the create-form
		model.addAttribute("event", new Event());
		return "events/event-form";
	}

	@PostMapping("/create")
	public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto,  @RequestParam("image_cstm") MultipartFile image, BindingResult result, Model model) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("event", eventDto);
			return "events/event-form";
		}
		eventDto.setImage(image.getBytes());
		eventService.createEvent(eventDto);
		return "redirect:/event/";
	}
}
