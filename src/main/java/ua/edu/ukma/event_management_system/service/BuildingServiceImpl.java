package ua.edu.ukma.event_management_system.service;

import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;

import java.util.ArrayList;
import java.util.List;

public class BuildingServiceImpl implements BuildingService {
    @Override
    public void addBuilding() {
        System.out.println("Created Building!");
    }
    @Override
    public List<Building> getAllBuildings() {
        List<Building> buildings = new ArrayList<Building>();
        buildings.add(new Building(1, "Liny Kostenko, 8", 500, 74, 50, "A small building"));
        buildings.add(new Building(2, "Hotkevycha, 27", 1450, 120, 100, "A medium building"));
        buildings.add(new Building(3, "Polubotka, 7", 750, 94, 85, "A medium building"));
        return buildings;
    }
}
