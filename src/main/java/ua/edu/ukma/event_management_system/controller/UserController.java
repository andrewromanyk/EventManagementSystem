package ua.edu.ukma.event_management_system.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.BuildingRating;
import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.BuildingRatingDto;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private ModelMapper modelMapper;
    private UserService userService;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setBuildingService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        return toDto(userService.getUserById(id));
    }

    @GetMapping("/")
    public List<UserDto> getUsers() {
        return userService.getAllUsers()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @PostMapping("/")
    public void createNewUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable long id) {
        userService.removeUser(id);
    }

    private UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
