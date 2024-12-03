package com.example.groupproject.services;

import com.example.groupproject.dto.PetDTO;
import com.example.groupproject.dto.PetSummary;
import com.example.groupproject.entities.Pet;
import com.example.groupproject.exceptions.ResourceNotFoundException;
import com.example.groupproject.repositories.HouseholdRepository;
import com.example.groupproject.repositories.PetRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PetService {

  private final PetRepository petRepository;
  private final HouseholdRepository householdRepository;

  public PetService(PetRepository petRepository, HouseholdRepository householdRepository) {
    this.petRepository = petRepository;
    this.householdRepository = householdRepository;
  }
  public List<PetDTO> findPetsByAnimalType(String animalType) {
    // Retrieve pets by animal type from the repository
    List<Pet> pets = petRepository.findByAnimalTypeIgnoreCase(animalType);

    // Map the list of Pet entities to PetDTOs
    return pets.stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }
  public PetDTO createPet(@Valid PetDTO petDTO) {
    Pet pet = mapToEntity(petDTO);
    Pet savedPet = petRepository.save(pet);
    return mapToDTO(savedPet);
  }
  public Double getAverageAge() {
    return petRepository.findAverageAge(); // Assuming this query exists in PetRepository
  }


  public PetDTO getPetById(Long id) {
    Pet pet = petRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Pet not found with ID: " + id));
    return mapToDTO(pet);
  }
  public Integer getOldestAge() {
    return petRepository.findOldestAge(); // Assuming this method exists in PetRepository
  }

  public List<PetDTO> getAllPets() {
    List<Pet> pets = petRepository.findAll();
    return pets.stream().map(this::mapToDTO).toList();
  }

  public void updatePetName(Long id, String newName) {
    Pet pet = petRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Pet not found with ID: " + id));
    pet.setName(newName);
    petRepository.save(pet);
  }

  public void deletePet(Long id) {
    if (!petRepository.existsById(id)) {
      throw new ResourceNotFoundException("Pet not found with ID: " + id);
    }
    petRepository.deleteById(id);
  }

  private PetDTO mapToDTO(Pet pet) {
    return new PetDTO(
        pet.getName(),
        pet.getAnimalType(),
        pet.getBreed(),
        pet.getAge(),
        pet.getHousehold() != null ? pet.getHousehold().getEircode() : null
    );
  }

  private Pet mapToEntity(PetDTO petDTO) {
    Pet pet = new Pet();
    pet.setName(petDTO.name());
    pet.setAnimalType(petDTO.animalType());
    pet.setBreed(petDTO.breed());
    pet.setAge(petDTO.age());
    // Fetch the household and associate it with the pet
    pet.setHousehold(
        petDTO.householdEircode() != null
            ? householdRepository.findById(petDTO.householdEircode())
            .orElseThrow(() -> new ResourceNotFoundException("Household not found"))
            : null
    );
    return pet;

  }
}
