package ua.edu.ukma.event_management_system.service;

import ua.edu.ukma.event_management_system.entity.User;
import ua.edu.ukma.event_management_system.entity.UserRole;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

import java.util.*;

public class UserServiceImpl implements UserService {
    @Override
    public void createUser(User user) {
        System.out.println("Created user: " + user.getFirstName());
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        users.add(new User(1, UserRole.USER, "petro123", "Petro", "Petrenko", 19));
        users.add(new User(2, UserRole.USER, "rose", "Alina", "Koval", 38));
        users.add(new User(1, UserRole.USER, "petro123", "Kyrylo", "Vovk", 51));
        return users;
    }

    @Override
    public boolean checkPermission(User user, UserRole role) {
        List<UserRole> list = Arrays.asList(UserRole.values());
        return list.indexOf(user.getUserRole()) >= list.indexOf(role);
    }

}
