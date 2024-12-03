package com.example.groupproject;

import com.example.groupproject.entities.Household;
import com.example.groupproject.services.HouseholdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HouseholdServiceTests {

  @Autowired
  private HouseholdService householdService;

  @Test
  void testFindByEircode() {
    Household household = householdService.findByEircode("D02XY45");
    assertNotNull(household);
    assertEquals(3, household.getNumberOfOccupants());
  }

  @Test
  void testFindWithPetsByEircode() {
    Household household = householdService.findWithPetsByEircode("D02XY45");
    assertNotNull(household);
    assertFalse(household.getPets().isEmpty());
  }

  @Test
  void testFindHouseholdsWithNoPets() {
    List<Household> households = householdService.findHouseholdsWithNoPets();
    assertNotNull(households);
    assertFalse(households.isEmpty());
  }
}
