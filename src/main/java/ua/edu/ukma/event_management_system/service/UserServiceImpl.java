package ua.edu.ukma.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.domain.UserRole;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.repository.UserRepository;
import ua.edu.ukma.event_management_system.service.interfaces.TicketService;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

import java.util.*;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketService ticketService;

    @Override
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void updateUser(UserEntity updatedUser) {
        Optional<UserEntity> existingUserOpt = userRepository.findById(updatedUser.getId());
        if (existingUserOpt.isPresent()) {
            UserEntity existingUser = existingUserOpt.get();
            existingUser.setUserRole(updatedUser.getUserRole());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
            existingUser.setAge(updatedUser.getAge());
            userRepository.save(existingUser);
        }
    }

    @Override
    public void removeUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean checkPermission(UserEntity user, UserRole role) {
        List<UserRole> list = Arrays.asList(UserRole.values());
        return list.indexOf(user.getUserRole()) >= list.indexOf(role);
    }

}
