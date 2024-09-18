package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.entity.User;
<<<<<<< HEAD
import ua.edu.ukma.event_management_system.entity.UserRole;

import java.util.List;

public interface UserService {
    void createUser(User user);
    List<User> getAllUsers();
    boolean checkPermission(User user, UserRole role);
=======

public interface UserService {

    void registerUser(User user);

    User getUserById(int userId);

    void updateUser(User updatedUser);

    void removeUser(int userId);

>>>>>>> 2b0c562c0d59a9357421f3f903eeeb26826c862e
}
