package ua.edu.ukma.event_management_system.service.interfaces;

import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;
import ua.edu.ukma.event_management_system.entity.BuildingRatingEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.repository.BuildingRatingRepository;

import java.util.List;

public interface BuildingService {

    BuildingEntity createBuilding(BuildingEntity building);

    List<BuildingEntity> getAllBuildings();

    BuildingEntity getBuildingById(Long id);

    void updateBuilding(Long id, BuildingEntity updatedBuilding);

    void deleteBuilding(Long id);

    BuildingRatingEntity rateBuilding(BuildingEntity building, byte rating, UserEntity user, String comment);

    List<BuildingRatingEntity> getAllByBuildingIdAndRating(long buildingId, byte rating);

    List<BuildingEntity> getAllByCapacity(int capacity);
}
