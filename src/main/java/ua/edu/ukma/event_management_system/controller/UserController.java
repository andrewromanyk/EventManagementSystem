package ua.edu.ukma.event_management_system.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
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
@ConditionalOnExpression("${api.user.enable}")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
        try{
            MDC.put("userId", String.valueOf(id));
            logger.info("Got user");
            return toDto(userService.getUserById(id));
        }finally{
            MDC.clear();
        }
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
        try {
            MDC.put("userId", String.valueOf(id));
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                });
                logger.error(errors.toString());
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
            logger.info("Put user");
            userService.updateUser(id, userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        }finally{
            MDC.clear();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        try {
            MDC.put("userId", String.valueOf(id));
            userService.removeUser(id);
            logger.info("Deleted user");
            return new ResponseEntity<>(HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    private UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
