package ua.edu.ukma.event_management_system.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.BuildingRating;
import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.BuildingRatingDto;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.*;

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
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        userService.updateUser(id, userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
