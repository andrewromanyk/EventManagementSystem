package ua.edu.ukma.event_management_system.dao;

import ua.edu.ukma.event_management_system.dao.interfaces.UserDao;
import ua.edu.ukma.event_management_system.entity.User;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void delete(User user) {

    }
}
