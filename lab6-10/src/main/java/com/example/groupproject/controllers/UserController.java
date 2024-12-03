package com.example.groupproject.controllers;

import com.example.groupproject.dto.PasswordResetDTO;
import com.example.groupproject.dto.UserDTO;
import com.example.groupproject.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  @Secured("ROLE_ADMIN")
  public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
    UserDTO createdUser = userService.createUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
  }

  @PatchMapping("/{id}/reset-password")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<Void> resetPassword(@PathVariable Long id, @RequestBody @Valid PasswordResetDTO dto) {
    userService.resetPassword(id, dto.newPassword()); // Correct accessor method
    return ResponseEntity.noContent().build();
  }


  @PatchMapping("/{id}/toggle-locked")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<Void> toggleLockedStatus(@PathVariable Long id) {
    userService.toggleLockedStatus(id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  @Secured("ROLE_ADMIN")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}

