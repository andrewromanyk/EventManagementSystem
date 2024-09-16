package ua.edu.ukma.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.event_management_system.dao.interfaces.BuildingDao;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingDao buildingDao;

    @Override
    public void addBuilding() {

    }
}
