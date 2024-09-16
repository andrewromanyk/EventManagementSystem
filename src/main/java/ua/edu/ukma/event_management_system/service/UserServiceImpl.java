package ua.edu.ukma.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.event_management_system.dao.interfaces.UserDao;
import ua.edu.ukma.event_management_system.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
}
