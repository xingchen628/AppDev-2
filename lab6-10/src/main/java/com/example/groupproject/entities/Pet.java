package com.example.groupproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "pets", schema = "pets")
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String animalType;

  private String breed;

  private int age;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "household_eircode", nullable = false)
  private Household household; // Establishes a Many-to-One relationship with Household
}
