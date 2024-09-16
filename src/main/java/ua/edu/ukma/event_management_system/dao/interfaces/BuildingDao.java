package ua.edu.ukma.event_management_system.dao.interfaces;

import ua.edu.ukma.event_management_system.entity.User;

import java.util.List;
import java.util.Optional;

public interface BuildingDao {
    List<User> findAll();

    Optional<User> findById(int id);

    void save(User user);

    void deleteById(int id);

    void delete(User user);
}
