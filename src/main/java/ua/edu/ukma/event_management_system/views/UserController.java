package ua.edu.ukma.event_management_system.views;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.domain.UserRole;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {
    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable long id, Model model) {
        UserDto user = toDto(userService.getUserById(id));
        model.addAttribute("user", user);
        return "users/user-details";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        UserDto user = toDto(userService.getUserById(id));
        model.addAttribute("userDto", user);
        model.addAttribute("roles", UserRole.values());
        return "users/user-form";
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        List<UserDto> users;
        users = userService.getAllUsers()
                .stream()
                .map(this::toDto)
                .toList();
        model.addAttribute("users", users);
        return "users/user-list";
    }

//    @PostMapping("/")
//    public String createNewBuilding(@ModelAttribute("buildingDto") @Valid UserDto buildingDto,
//                                    BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()){
//            List<String> errors = new ArrayList<>();
//            bindingResult.getFieldErrors().forEach(error -> {
//                String errorMessage = "Error for " + error.getField() + " field: " + error.getDefaultMessage();
//                errors.add(errorMessage);
//            });
//
//            model.addAttribute("errors", errors);
//            return "error";
//        }
//
//        if(buildingDto.getDescription() == null || buildingDto.getDescription().isEmpty()){
//            RestClient client = RestClient.create();
//            String defaultDescription = client.get()
//                    .uri("https://baconipsum.com/api/?type=meat-and-filler&sentences=2&format=text")
//                    .retrieve()
//                    .body(String.class);
//            buildingDto.setDescription(defaultDescription);
//        }
//
//        buildingService.createBuilding(buildingDto);
//        return "redirect:/building/";
//    }
//
//    @GetMapping("/create")
//    public String createBuildingForm(Model model) {
//        model.addAttribute("buildingDto", new BuildingDto());
//        return "buildings/building-form";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String editBuildingForm(@PathVariable long id, Model model){
//        BuildingDto buildingDto = toDto(buildingService.getBuildingById(id));
//        model.addAttribute("buildingDto", buildingDto);
//        return "buildings/building-form";
//    }
//
//    @PutMapping("/{id}")
//    public String updateBuilding(@PathVariable long id, @Valid BuildingDto buildingDto,
//                                 BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()){
//            List<String> errors = new ArrayList<>();
//            bindingResult.getFieldErrors().forEach(error -> {
//                String errorMessage = "Error for " + error.getField() + " field: " + error.getDefaultMessage();
//                errors.add(errorMessage);
//            });
//            model.addAttribute("errors", errors);
//            return "error";
//        }
//        buildingService.updateBuilding(id, buildingDto);
//        return "redirect:/building/";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteBuilding(@PathVariable long id, Model model) {
//        try{
//            buildingService.deleteBuilding(id);
//        }catch(DataIntegrityViolationException ex){
//            List<String> errors = new ArrayList<>();
//            errors.add("Cannot delete building. It is referenced by other entities.");
//            model.addAttribute("errors", errors);
//            return "error";
//        }
//        return "redirect:/building/";
//    }
    //
//    @PostMapping("/{id}/rate")
//    public void rateBuilding(@PathVariable long id) {
//    }
//
//    @GetMapping("/{buildingId}/{rating}")
//    @ResponseBody
//    public List<BuildingRatingDto> getRated(@PathVariable long buildingId, @PathVariable byte rating) {
//        return buildingService.getAllByBuildingIdAndRating(buildingId, rating)
//                .stream()
//                .map(this::toDto)
//                .toList();
//    }
//
    private UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

//    private B toDto(BuildingRating buildingRating) {
//        return modelMapper.map(buildingRating, BuildingRatingDto.class);
//    }
}