package com.example.groupproject.services;

import com.example.groupproject.dto.UserDTO;
import com.example.groupproject.entities.User; // Your custom User entity
import com.example.groupproject.exceptions.ResourceNotFoundException;
import com.example.groupproject.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }
  public UserDTO createUser(UserDTO userDTO) {
    User user = new User();
    user.setUsername(userDTO.username()); // Correct accessor method for records
    user.setPassword(passwordEncoder.encode(userDTO.password())); // Correct accessor
    user.setRole(userDTO.role());
    user.setFirstName(userDTO.firstName());
    user.setLastName(userDTO.lastName());
    user.setCounty(userDTO.county());
    user.setLocked(false);
    User savedUser = userRepository.save(user);
    return mapToDTO(savedUser);
  }

  public void resetPassword(Long userId, String newPassword) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
  }

  public void toggleLockedStatus(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    user.setLocked(!user.isLocked());
    userRepository.save(user);
  }

  public void deleteUser(Long userId) {
    if (!userRepository.existsById(userId)) {
      throw new ResourceNotFoundException("User not found with ID: " + userId);
    }
    userRepository.deleteById(userId);
  }

  private UserDTO mapToDTO(User user) {
    return new UserDTO(user.getId(), user.getUsername(), user.getFirstName(),
        user.getLastName(), user.getCounty(), user.getRole(),null);
  }
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // Fetch user from your database
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    // Create a Spring Security User object
    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getUsername())
        .password(user.getPassword()) // Assume the password is already encoded
        .roles(user.getRole()) // Role must be prefixed with "ROLE_" if you're using Spring Security defaults
        .disabled(user.isLocked()) // Locked users will be treated as disabled
        .build();
  }
}
