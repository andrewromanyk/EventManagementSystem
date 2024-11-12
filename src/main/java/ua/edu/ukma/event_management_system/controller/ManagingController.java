package ua.edu.ukma.event_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("manage")
public class ManagingController {
    @GetMapping
    public String getManagingPage(){
        return "manager_chooser";
    }
}
