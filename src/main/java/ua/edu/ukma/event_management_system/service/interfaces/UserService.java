package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.domain.UserRole;

import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> getAllUsers();

    User getUserById(int userId);

    void updateUser(User updatedUser);

    void removeUser(int userId);

    boolean checkPermission(User user, UserRole role);
}
