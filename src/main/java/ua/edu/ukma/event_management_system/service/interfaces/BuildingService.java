package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.entity.Building;

import java.util.List;

public interface BuildingService {
    void addBuilding();
    List<Building> getAllBuildings();
}
