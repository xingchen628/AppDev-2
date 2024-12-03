package com.example.groupproject.controllers;

import com.example.groupproject.dto.ChangePetNameDTO;
import com.example.groupproject.dto.PetDTO;
import com.example.groupproject.services.PetService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

  private final PetService petService;

  /**
   * Get all pets
   * @return List of all PetDTOs
   */
  @GetMapping
  public ResponseEntity<List<PetDTO>> getAllPets() {
    List<PetDTO> pets = petService.getAllPets();
    return ResponseEntity.ok(pets); // HTTP 200
  }

  /**
   * Get a specific pet by ID
   * @param id Pet ID
   * @return PetDTO for the given ID
   */
  @GetMapping("/{id}")
  public ResponseEntity<PetDTO> getPet(@PathVariable Long id) {
    PetDTO pet = petService.getPetById(id);
    return ResponseEntity.ok(pet); // HTTP 200
  }

  /**
   * Create a new pet
   * @param petDTO PetDTO containing pet details
   * @return Created PetDTO
   */
  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<PetDTO> createPet(@Valid @RequestBody PetDTO petDTO) {
    PetDTO createdPet = petService.createPet(petDTO);
    return ResponseEntity.status(201).body(createdPet); // HTTP 201
  }

  /**
   * Update a pet's name
   * @param id Pet ID
   * @param dto ChangePetNameDTO containing the new name
   * @return HTTP 204 No Content
   */
  @PatchMapping("/{id}")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<Void> changePetName(@PathVariable Long id, @RequestBody @Valid ChangePetNameDTO dto) {
    petService.updatePetName(id, dto.name());
    return ResponseEntity.noContent().build(); // HTTP 204
  }

  /**
   * Delete a pet by ID
   * @param id Pet ID
   * @return HTTP 204 No Content
   */
  @DeleteMapping("/{id}")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<Void> deletePet(@PathVariable Long id) {
    petService.deletePet(id);
    return ResponseEntity.noContent().build(); // HTTP 204
  }
}
