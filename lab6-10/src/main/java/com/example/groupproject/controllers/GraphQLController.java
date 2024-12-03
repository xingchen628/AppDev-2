package com.example.groupproject.controllers;

import com.example.groupproject.dto.HouseholdDTO;
import com.example.groupproject.dto.PetDTO;
import com.example.groupproject.entities.Household;
import com.example.groupproject.entities.Statistics;
import com.example.groupproject.services.HouseholdService;
import com.example.groupproject.services.PetService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLController {

  private final HouseholdService householdService;
  private final PetService petService;

  public GraphQLController(HouseholdService householdService, PetService petService) {
    this.householdService = householdService;
    this.petService = petService;
  }

  @QueryMapping
  public List<HouseholdDTO> getAllHouseholds() {
    return householdService.getAllHouseholds();
  }
  @QueryMapping
  public Statistics getStatistics() {
    Double averageAge = petService.getAverageAge();
    Integer oldestAge = petService.getOldestAge();
    return new Statistics(averageAge, oldestAge);
  }
  @QueryMapping
  public List<PetDTO> getPetsByAnimalType(@Argument String animalType) {
    return petService.findPetsByAnimalType(animalType);
  }

  @QueryMapping
  public HouseholdDTO getHousehold(@Argument String eircode) {
    return householdService.getHouseholdByEircode(eircode);
  }

  @QueryMapping
  public PetDTO getPet(@Argument Long id) {
    return petService.getPetById(id);
  }

  @MutationMapping
  @Secured("ROLE_ADMIN")
  public HouseholdDTO createHousehold(@Argument HouseholdDTO input) {
    return householdService.createHousehold(input);
  }

  @MutationMapping
  @Secured("ROLE_ADMIN")
  public String deleteHousehold(@Argument String eircode) {
    householdService.deleteHousehold(eircode);
    return "Household deleted successfully.";
  }

  @MutationMapping
  @Secured("ROLE_ADMIN")
  public String deletePet(@Argument Long id) {
    petService.deletePet(id);
    return "Pet deleted successfully.";
  }

  @SchemaMapping(typeName = "Household", field = "pets")
  public List<PetDTO> pets(Household household) {
    return household.getPets().stream()
        .map(pet -> new PetDTO(
            pet.getName(),
            pet.getAnimalType(),
            pet.getBreed(),
            pet.getAge(),
            household.getEircode()))
        .collect(Collectors.toList());
  }
}
