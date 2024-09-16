package ua.edu.ukma.event_management_system.dao;

import org.springframework.stereotype.Component;
import ua.edu.ukma.event_management_system.dao.interfaces.BuildingDao;
import ua.edu.ukma.event_management_system.entity.User;

import java.util.List;
import java.util.Optional;

@Component
public class BuildingDaoImpl implements BuildingDao {
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
