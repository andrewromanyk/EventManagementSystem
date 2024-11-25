package ua.edu.ukma.event_management_system.views;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import ua.edu.ukma.event_management_system.domain.Building;
import ua.edu.ukma.event_management_system.domain.BuildingRating;
import ua.edu.ukma.event_management_system.dto.BuildingDto;
import ua.edu.ukma.event_management_system.dto.BuildingRatingDto;
import ua.edu.ukma.event_management_system.service.interfaces.BuildingService;
import jakarta.validation.Valid;

import java.util.*;

@Controller
@RequestMapping("building")
@ConditionalOnExpression("${api.building.enable}")
public class BuildingViewController {

    private ModelMapper modelMapper;
    private BuildingService buildingService;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setBuildingService(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/{id}")
    public String getBuilding(@PathVariable long id, Model model) {
        BuildingDto building = toDto(buildingService.getBuildingById(id));

        List<BuildingRatingDto> ratings = building.getRating()
                .stream()
                .map(rId -> toDto(buildingService.getRatingById(rId)))
                .toList();

        model.addAttribute("building", building);
        model.addAttribute("ratings", ratings);
        return "buildings/building-details";
    }

    @GetMapping("/")
    public String getBuildings(@RequestParam(required = false) Integer capacity, Model model) {
        List<BuildingDto> buildings;
        if (capacity == null) {
            buildings = buildingService.getAllBuildings()
                    .stream()
                    .map(this::toDto)
                    .toList();
        } else {
            buildings = buildingService.getAllByCapacity(capacity)
                    .stream()
                    .map(this::toDto)
                    .toList();
        }

        Map<Integer, Double> buildingAvgRating = new HashMap<>();
        buildings.forEach(b -> {
            if (b.getRating() != null && !b.getRating().isEmpty()) {
                buildingAvgRating.put(
                        b.getId(),
                        b.getRating()
                                .stream()
                                .mapToInt(rId -> buildingService.getRatingById(rId).getRating())
                                .average()
                                .getAsDouble()
                );
            }
        });

        model.addAttribute("buildings", buildings);
        model.addAttribute("avgRatings", buildingAvgRating);
        return "buildings/building-list";
    }

    @PostMapping("/")
    public String createNewBuilding(@ModelAttribute("buildingDto") @Valid BuildingDto buildingDto,
                                    BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(error -> {
                String errorMessage = "Error for " + error.getField() + " field: " + error.getDefaultMessage();
                errors.add(errorMessage);
            });

            model.addAttribute("errors", errors);
            return "error";
        }

        if(buildingDto.getDescription() == null || buildingDto.getDescription().isEmpty()){
            RestClient client = RestClient.create();
            String defaultDescription = client.get()
                    .uri("https://baconipsum.com/api/?type=meat-and-filler&sentences=2&format=text")
                    .retrieve()
                    .body(String.class);
            buildingDto.setDescription(defaultDescription);
        }

        buildingService.createBuilding(buildingDto);
        return "redirect:/building/";
    }

    @GetMapping("/create")
    public String createBuildingForm(Model model) {
        model.addAttribute("buildingDto", new BuildingDto());
        return "buildings/building-form";
    }

//    @PutMapping("/{id}")
//    @ResponseBody
//    public ResponseEntity<?> updateBuilding(@PathVariable long id, @RequestBody @Valid BuildingDto buildingDto,
//                                            BindingResult bindingResult) {
//        if(bindingResult.hasErrors()){
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(error -> {
//                errors.put(error.getField(), error.getDefaultMessage());
//            });
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }
//        buildingService.updateBuilding(id, buildingDto);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
    @DeleteMapping("/{id}")
    public String deleteBuilding(@PathVariable long id, Model model) {
        try{
            buildingService.deleteBuilding(id);
        }catch(DataIntegrityViolationException ex){
            List<String> errors = new ArrayList<>();
            errors.add("Cannot delete building. It is referenced by other entities.");
            model.addAttribute("errors", errors);
            return "error";
        }
        return "redirect:/building/";
    }
//
//    @PostMapping("/{id}/rate")
//    public void rateBuilding(@PathVariable long id) {
//    }
//
//    @GetMapping("/{buildingId}/{rating}")
//    @ResponseBody
//    public List<BuildingRatingDto> getRated(@PathVariable long buildingId, @PathVariable byte rating) {
//        return buildingService.getAllByBuildingIdAndRating(buildingId, rating)
//                .stream()
//                .map(this::toDto)
//                .toList();
//    }
//
    private BuildingDto toDto(Building building) {
        return modelMapper.map(building, BuildingDto.class);
    }

    private BuildingRatingDto toDto(BuildingRating buildingRating) {
        return modelMapper.map(buildingRating, BuildingRatingDto.class);
    }
}