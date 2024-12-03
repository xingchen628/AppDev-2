package com.example.groupproject.repositories;

import com.example.groupproject.dto.PetSummary;
import com.example.groupproject.entities.Pet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
  List<Pet> findByAnimalTypeIgnoreCase(String animalType);
  List<Pet> findByBreedOrderByAgeAsc(String breed);
  List<Pet> findByNameIgnoreCase(String name);
  @Transactional
  void deleteByNameIgnoreCase(String name);
  @Query("SELECT new com.example.groupproject.dto.PetSummary(p.name, p.animalType, p.breed) FROM Pet p")
  List<PetSummary> findPetSummaries();
  @Query("SELECT AVG(p.age) FROM Pet p")
  Double findAverageAge();
  @Query("SELECT MAX(p.age) FROM Pet p")
  Integer findOldestAge();

}
