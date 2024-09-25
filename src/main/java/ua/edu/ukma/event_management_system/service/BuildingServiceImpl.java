package ua.edu.ukma.event_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.repository.BuildingRepository;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

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
}
