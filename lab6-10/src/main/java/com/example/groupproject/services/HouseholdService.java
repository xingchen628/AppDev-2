package com.example.groupproject.services;

import com.example.groupproject.dto.HouseholdDTO;
import com.example.groupproject.entities.Household;
import com.example.groupproject.exceptions.ResourceNotFoundException;
import com.example.groupproject.repositories.HouseholdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseholdService {

  private final HouseholdRepository householdRepository;

  public HouseholdService(HouseholdRepository householdRepository) {
    this.householdRepository = householdRepository;
  }

  public HouseholdDTO getHouseholdByEircode(String eircode) {
    Household household = householdRepository.findById(eircode)
        .orElseThrow(() -> new ResourceNotFoundException("Household not found for eircode: " + eircode));
    return mapToDTO(household);
  }

  public List<HouseholdDTO> getHouseholdsWithNoPets() {
    List<Household> households = householdRepository.findByPetsIsNull();
    return households.stream().map(this::mapToDTO).collect(Collectors.toList());
  }

  public HouseholdDTO createHousehold(HouseholdDTO householdDTO) {
    Household household = mapToEntity(householdDTO);
    Household savedHousehold = householdRepository.save(household);
    return mapToDTO(savedHousehold);
  }

  public void deleteHousehold(String eircode) {
    if (!householdRepository.existsById(eircode)) {
      throw new ResourceNotFoundException("Household not found for eircode: " + eircode);
    }
    householdRepository.deleteById(eircode);
  }
  public List<HouseholdDTO> getAllHouseholds() {
    List<Household> households = householdRepository.findAll(); // Fetch all households from the database
    return households.stream()
        .map(this::mapToDTO) // Convert each Household entity to a DTO
        .collect(Collectors.toList());
  }

  private HouseholdDTO mapToDTO(Household household) {
    return new HouseholdDTO(
        household.getEircode(),
        household.getNumberOfOccupants(),
        household.getMaxNumberOfOccupants(),
        household.isOwnerOccupied()
    );
  }

  private Household mapToEntity(HouseholdDTO householdDTO) {
    return new Household(
        householdDTO.eircode(),
        householdDTO.numberOfOccupants(),
        householdDTO.maxNumberOfOccupants(),
        householdDTO.ownerOccupied(),
        null // Pass null for pets, as they are not part of the DTO
    );
  }
}
