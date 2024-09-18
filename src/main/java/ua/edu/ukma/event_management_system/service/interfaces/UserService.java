package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.entity.User;
import ua.edu.ukma.event_management_system.entity.UserRole;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
    boolean checkPermission(User user, UserRole role);
}
