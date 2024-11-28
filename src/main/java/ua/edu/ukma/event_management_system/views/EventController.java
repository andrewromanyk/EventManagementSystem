package ua.edu.ukma.event_management_system.views;

import org.slf4j.Logger;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.domain.Ticket;
import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.EventDto;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("event")
public class EventController {

	private static final String DATA_IMAGE = "data:image/png;base64,";
	private static final String STOCK_PHOTO = "src/main/resources/stock_photo.jpg";
	private static final String EVENT_FORM = "events/event-form";
	private static final String EVENT = "event";
	private static final String ERROR = "error";
	private static final String REDIRECT_EVENT = "redirect:/event/";
	private static final String BUILDINGS = "buildings";

	private ModelMapper modelMapper;
	private EventService eventService;
	private BuildingService buildingService;
	private UserService userService;

	private final Logger log = LoggerFactory.getLogger(EventController.class);

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	@Autowired
	public void setBuildingService(BuildingService buildingService) {
		this.buildingService = buildingService;
	}
	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@GetMapping("/")
	public String get(Model model) throws IOException {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUsername(details.getUsername());
		List<Event> events = switch (user.getUserRole()) {
            case ADMIN -> eventService.getAllEvents();
            case ORGANIZER -> eventService.getAllForOrganizer(user.getId());
            case USER -> eventService.getAllRelevant();
        };

		// Prepare a map of event IDs to Base64-encoded images
		Map<Integer, String> imageMap = new HashMap<>();
		for (Event event : events) {
			if (event.getImage() != null) {
				String base64Image = DATA_IMAGE + Base64.getEncoder().encodeToString(event.getImage());
				imageMap.put(event.getId(), base64Image);
			}
			else {
				String base64Image = DATA_IMAGE + Base64.getEncoder().encodeToString(Files.readAllBytes(Path.of(STOCK_PHOTO)));
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
		UserDetails detail;
		User user = null;
		try {
			detail = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			user = userService.getUserByUsername(detail.getUsername());
		} catch (Exception e) {
			log.info("User is not authorized.");
		}

		// Prepare a map of event IDs to Base64-encoded images
		String base64Image;
		if (event.getImage() != null) {
			base64Image = DATA_IMAGE + Base64.getEncoder().encodeToString(event.getImage());
		}
		else {
			base64Image = DATA_IMAGE + Base64.getEncoder().encodeToString(Files.readAllBytes(Path.of(STOCK_PHOTO)));
		}

		model.addAttribute(EVENT, event);
		model.addAttribute("isAllowedToBuy", user == null || user.getAge() >= event.getMinAgeRestriction());
		model.addAttribute("img", base64Image);
		return "events/event";
	}

	@GetMapping("/create")
	public String showCreateEventForm(Model model) {
		List<BuildingDto> buildings = buildingService.getAllBuildings()
				.stream()
				.map(this::toDto)
				.toList();
		model.addAttribute(BUILDINGS, buildings);
		model.addAttribute(EVENT, new EventDto());
		return EVENT_FORM;
	}

	@PostMapping("/create")
	public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto,  @RequestParam("image_cstm") MultipartFile image,
							  BindingResult result, Model model, RedirectAttributes redirectAttributes) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute(EVENT, eventDto);
			return EVENT_FORM;
		}
		if (eventDto.getDateTimeEnd().isBefore(eventDto.getDateTimeStart())) {
			model.addAttribute(EVENT, eventDto);
			model.addAttribute(ERROR, "Event cannot end before it started!");
			return EVENT_FORM;
		}
		if (!eventService.getAllThatIntersect(eventDto.getDateTimeStart(), eventDto.getDateTimeEnd(), eventDto.getBuilding()).isEmpty()) {
			model.addAttribute(EVENT, eventDto);
			model.addAttribute(ERROR, "Building is already occupied for that time");
			return EVENT_FORM;
		}
		eventDto.setImage(image.getBytes());
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUsername(details.getUsername());
		eventDto.setCreator(user.getId());
		eventService.createEvent(eventDto);
		return REDIRECT_EVENT;
	}

	@DeleteMapping("/{id}")
	public String deleteEvent(@PathVariable long id, RedirectAttributes redirectAttributes) {
		try {
			eventService.deleteEvent(id);
			redirectAttributes.addFlashAttribute("successMessage", "Event successfully deleted.");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete the event. Please try again.");
		}
		return REDIRECT_EVENT;
	}

	@GetMapping("/{id}/buy-ticket")
	public String buyTicket(@PathVariable long id, Model model) {
		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUsername(details.getUsername());

		Event event = eventService.getEventById(id);

		Ticket ticket = new Ticket();
		ticket.setUser(user);
		ticket.setEvent(event);
		ticket.setPrice(event.getPrice());

		model.addAttribute("ticket", ticket);
		model.addAttribute(EVENT, event);
		return "tickets/ticket-form";
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable long id, Model model) {
		Event event = eventService.getEventById(id);
		List<BuildingDto> buildings = buildingService.getAllBuildings()
				.stream()
				.map(this::toDto)
				.toList();
		String formattedDate1 = event.getDateTimeStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
		String formattedDate2= event.getDateTimeEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
		model.addAttribute("dateTimeStartString", formattedDate1);
		model.addAttribute("dateTimeEndString", formattedDate2);
		model.addAttribute(BUILDINGS, buildings);
		model.addAttribute(EVENT, event);
		return EVENT_FORM;
	}

	@PutMapping("/{id}")
	public String editPut(@PathVariable long id, @ModelAttribute EventDto event, @RequestParam("image_cstm") MultipartFile image, Model model) throws IOException {
		if (image.isEmpty()) {
			event.setImage(eventService.getEventById(id).getImage());
		}
		if (event.getDateTimeEnd().isBefore(event.getDateTimeStart())) {
			model.addAttribute(EVENT, event);
			model.addAttribute(ERROR, "Event cannot end before it started!");
			List<BuildingDto> buildings = buildingService.getAllBuildings()
					.stream()
					.map(this::toDto)
					.toList();
			model.addAttribute(BUILDINGS, buildings);
			return EVENT_FORM;
		}
		if (!eventService.getAllThatIntersect(event.getDateTimeStart(), event.getDateTimeEnd(), event.getBuilding()).isEmpty()) {
			model.addAttribute(EVENT, event);
			model.addAttribute(ERROR, "Building is already occupied for that time");
			List<BuildingDto> buildings = buildingService.getAllBuildings()
					.stream()
					.map(this::toDto)
					.toList();
			model.addAttribute(BUILDINGS, buildings);
			return EVENT_FORM;
		}
		else event.setImage(image.getBytes());
		eventService.updateEvent(id, event);
		return REDIRECT_EVENT;
	}

	private BuildingDto toDto(Building building) {
		return modelMapper.map(building, BuildingDto.class);
	}

}
