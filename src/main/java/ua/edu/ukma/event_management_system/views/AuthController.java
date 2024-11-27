package ua.edu.ukma.event_management_system.views;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.event_management_system.domain.UserRole;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

@Controller
public class AuthController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("register")
    public String index(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("register")
    public String registerUser(@ModelAttribute @Valid UserDto userDto) {
        userDto.setUserRole(UserRole.USER);
        userService.createUser(userDto);
        return "redirect:/login";
    }
}
