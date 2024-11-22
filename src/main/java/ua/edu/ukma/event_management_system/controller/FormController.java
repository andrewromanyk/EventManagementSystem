package ua.edu.ukma.event_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.service.interfaces.TicketService;


@Controller
public class FormController {

    private TicketService ticketService;

    @Autowired
    public void setTicketService(TicketService service){
        this.ticketService = service;
    }

    @GetMapping("/building-form")
    public String createBuildingForm(Model model) {
        model.addAttribute("buildingDto", new BuildingDto());
        return "building-form";
    }

    @GetMapping("/main")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("tickets", ticketService.getAllTicketsForUser(userDetails.getUsername()));
        return "main";
    }
}