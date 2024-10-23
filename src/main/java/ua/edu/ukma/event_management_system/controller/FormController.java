package ua.edu.ukma.event_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import ua.edu.ukma.event_management_system.dto.BuildingDto;


@Controller
public class FormController {

    @GetMapping("/create-building")
    public String createBuildingForm(Model model) {
        model.addAttribute("buildingDto", new BuildingDto());
        return "building-form";
    }
}