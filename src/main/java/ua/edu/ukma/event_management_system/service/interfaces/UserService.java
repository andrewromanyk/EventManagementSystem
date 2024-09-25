package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.domain.UserRole;
import ua.edu.ukma.event_management_system.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity user);

    List<UserEntity> getAllUsers();

    UserEntity getUserById(long userId);

    void updateUser(UserEntity updatedUser);

    void removeUser(long userId);

    boolean checkPermission(UserEntity user, UserRole role);
}
