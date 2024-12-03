package com.example.groupproject.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username; // Email

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String role; // e.g., "USER", "ADMIN"

  @Column(nullable = false)
  private boolean locked; // false = active, true = locked

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String county; // e.g., "Cork", "Kerry"
}

