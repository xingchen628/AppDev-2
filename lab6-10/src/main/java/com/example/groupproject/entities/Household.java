package com.example.groupproject.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "household", schema = "pets")
public class Household {

  @Id
  @Column(name = "eircode")
  private String eircode;

  @Column(name = "number_of_occupants")
  private int numberOfOccupants;

  @Column(name = "max_number_of_occupants")
  private int maxNumberOfOccupants;

  @Column(name = "owner_occupied")
  private boolean ownerOccupied;

  @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Pet> pets;
}
