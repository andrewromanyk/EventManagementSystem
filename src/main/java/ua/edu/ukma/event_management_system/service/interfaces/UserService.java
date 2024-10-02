package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.domain.UserRole;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.entity.UserEntity;

import java.util.List;

public interface UserService {

    User createUser(UserDto user);

    List<User> getAllUsers();

    User getUserById(long userId);

    void updateUser(long id, UserDto updatedUser);

    void removeUser(long userId);

    boolean checkPermission(User user, UserRole role);
}
