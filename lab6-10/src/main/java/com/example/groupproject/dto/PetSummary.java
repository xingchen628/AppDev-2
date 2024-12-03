package com.example.groupproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PetSummary {
  private String name;
  private String animalType;
  private String breed;
}
