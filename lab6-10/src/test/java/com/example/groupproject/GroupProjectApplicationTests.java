package com.example.groupproject;

import com.example.groupproject.dto.PetSummary;
import com.example.groupproject.repositories.PetRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class GroupProjectApplicationTests {

  @Autowired
  private PetRepository petRepository;

  @Test
  public void testFindPetSummaries() {
    List<PetSummary> summaries = petRepository.findPetSummaries();
    Assertions.assertNotNull(summaries, "Summaries should not be null");
    Assertions.assertFalse(summaries.isEmpty(), "Summaries should not be empty");
  }

  @Test
  public void testFindAverageAge() {
    Double avgAge = petRepository.findAverageAge();
    Assertions.assertNotNull(avgAge, "Average age should not be null");
  }

  @Test
  public void testFindOldestAge() {
    Integer oldestAge = petRepository.findOldestAge();
    Assertions.assertNotNull(oldestAge, "Oldest age should not be null");
  }
}
