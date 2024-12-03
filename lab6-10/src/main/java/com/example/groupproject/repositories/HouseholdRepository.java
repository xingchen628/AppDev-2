package com.example.groupproject.repositories;

import com.example.groupproject.entities.Household;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, String> {

  @EntityGraph(attributePaths = "pets")
  Household findWithPetsByEircode(String eircode);

  List<Household> findByPetsIsNull();

  Household save(Household household);

  List<Household> findAll();

  @Query("SELECT h FROM Household h JOIN FETCH h.pets WHERE h.eircode = :eircode")
  Optional<Household> findByIdWithPets(@Param("eircode") String eircode);

  void deleteById(String eircode);

  @Query("SELECT h FROM Household h WHERE h.pets IS EMPTY")
  List<Household> findHouseholdsWithNoPets();

  List<Household> findByOwnerOccupiedTrue();

  @Query("SELECT COUNT(h) FROM Household h WHERE h.numberOfOccupants = 0")
  int countEmptyHouseholds();

  @Query("SELECT COUNT(h) FROM Household h WHERE h.numberOfOccupants = h.maxNumberOfOccupants")
  int countFullHouseholds();
}
