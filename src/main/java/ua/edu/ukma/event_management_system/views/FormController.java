package ua.edu.ukma.event_management_system.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import ua.edu.ukma.event_management_system.domain.Event;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;
import ua.edu.ukma.event_management_system.service.interfaces.TicketService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class FormController {

    private EventService eventService;

    @Autowired
    public void setTicketService(EventService service){
        this.eventService = service;
    }

    @GetMapping("/main")
    public String mainPage(Model model) throws IOException {
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
        return "main";
    }
}