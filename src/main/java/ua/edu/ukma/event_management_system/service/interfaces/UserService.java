package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.entity.User;

public interface UserService {

    void registerUser(User user);

    User getUserById(int userId);

    void updateUser(User updatedUser);

    void removeUser(int userId);

}
