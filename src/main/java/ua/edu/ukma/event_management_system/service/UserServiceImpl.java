package ua.edu.ukma.event_management_system.service;

import org.springframework.stereotype.Component;
import ua.edu.ukma.event_management_system.entity.User;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

@Component
public class UserServiceImpl implements UserService {
    @Override
    public void registerUser(User user) {

    }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public void updateUser(User updatedUser) {

    }

    @Override
    public void removeUser(int userId) {

    }
}
