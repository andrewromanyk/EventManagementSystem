package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;

import java.util.List;

public interface BuildingService {

    BuildingEntity createBuilding(BuildingEntity building);

    List<BuildingEntity> getAllBuildings();

    BuildingEntity getBuildingById(Long id);

    void updateBuilding(Long id, BuildingEntity updatedBuilding);

    void deleteBuilding(Long id);

}
