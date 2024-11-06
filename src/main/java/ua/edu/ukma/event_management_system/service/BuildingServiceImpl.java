package ua.edu.ukma.event_management_system.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.edu.ukma.event_management_system.aop.rate_limit.RateLimit;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.BuildingRating;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.entity.BuildingEntity;
import ua.edu.ukma.event_management_system.entity.BuildingRatingEntity;
import ua.edu.ukma.event_management_system.entity.UserEntity;
import ua.edu.ukma.event_management_system.repository.BuildingRatingRepository;
import ua.edu.ukma.event_management_system.repository.BuildingRepository;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BuildingServiceImpl implements BuildingService {

    private ModelMapper modelMapper;
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

    @Autowired
    void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Building createBuilding(BuildingDto building) {
        BuildingEntity toSave = dtoToEntity(building);
        return toDomain(buildingRepository.save(toSave));
    }

    @Override
    @RateLimit(maxRequests = 3)
    public List<Building> getAllBuildings() {
        return buildingRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Building getBuildingById(Long id) {
		return toDomain(buildingRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("Building not found: " + id)));
    }

    @Override
    public void updateBuilding(Long id, BuildingDto updatedBuilding) {
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
    public BuildingRating rateBuilding(BuildingDto building, byte rating, UserDto user, String comment) {
        return toDomain(buildingRatingRepository.save(
                        new BuildingRatingEntity(
                                dtoToEntity(building),
                                rating,
                                dtoToEntity(user),
                                comment
                        )
                )
        );
    }

    @Override
    public List<BuildingRating> getAllByBuildingIdAndRating(long buildingId, byte rating) {
        return buildingRatingRepository.findAllByBuildingIdAndRating(buildingId, rating)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Building> getAllByCapacity(int capacity) {
        return buildingRepository.findAllByCapacity(capacity)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private BuildingDto toDto(Building building) {
        return modelMapper.map(building, BuildingDto.class);
    }

    private Building toDomain(BuildingEntity buildingEntity) {
        return modelMapper.map(buildingEntity, Building.class);
    }

    private BuildingRating toDomain(BuildingRatingEntity buildingRatingEntity) {
        return modelMapper.map(buildingRatingEntity, BuildingRating.class);
    }

    private BuildingEntity toEntity(Building building) {
        return modelMapper.map(building, BuildingEntity.class);
    }

    private BuildingEntity dtoToEntity(BuildingDto buildingDto) {
        return modelMapper.map(buildingDto, BuildingEntity.class);
    }

    private UserEntity dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }
}
