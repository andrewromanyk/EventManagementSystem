package ua.edu.ukma.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;
import ua.edu.ukma.event_management_system.entity.BuildingRatingEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.repository.BuildingRatingRepository;
import ua.edu.ukma.event_management_system.repository.BuildingRepository;
import ua.edu.ukma.event_management_system.repository.UserRepository;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {

    private BuildingRepository buildingRepository;
    private BuildingRatingRepository buildingRatingRepository;

    @Autowired
    void setBuildingRepository(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Autowired
    void setBuildingRatingRepository(BuildingRatingRepository buildingRatingRepository) {
        this.buildingRatingRepository = buildingRatingRepository;
    }

    @Override
    public BuildingEntity createBuilding(BuildingEntity building) {
        return buildingRepository.save(building);
    }

    @Override
    public List<BuildingEntity> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public BuildingEntity getBuildingById(Long id) {
        BuildingEntity building = buildingRepository.findById(id).get();
        return building;
    }

    @Override
    public void updateBuilding(Long id, BuildingEntity updatedBuilding) {
        Optional<BuildingEntity> existingBuilding = buildingRepository.findById(id);
        if (existingBuilding.isPresent()) {
            BuildingEntity building = existingBuilding.get();
            building.setAddress(updatedBuilding.getAddress());
            building.setHourlyRate(updatedBuilding.getHourlyRate());
            building.setAreaM2(updatedBuilding.getAreaM2());
            building.setCapacity(updatedBuilding.getCapacity());
            building.setDescription(updatedBuilding.getDescription());
            buildingRepository.save(building);
        }
    }

    @Override
    public void deleteBuilding(Long id) {
        buildingRepository.deleteById(id);
    }

    @Override
    public BuildingRatingEntity rateBuilding(BuildingEntity building, byte rating, UserEntity user, String comment) {
        return buildingRatingRepository.save(new BuildingRatingEntity(building, rating, user, comment));
    }
}
