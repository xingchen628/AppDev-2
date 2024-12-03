package com.example.groupproject.config;

import com.example.groupproject.repositories.UserRepository;
import com.example.groupproject.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.context.annotation.Lazy;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  private final UserService userService;

  public SecurityConfig(@Lazy UserService userService) { // Use @Lazy to defer initialization
    this.userService = userService;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    UserDetails user = User.withUsername("user")
        .password(passwordEncoder.encode("password"))
        .roles("USER")
        .build();

    UserDetails admin = User.withUsername("admin")
        .password(passwordEncoder.encode("admin"))
        .roles("USER", "ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user, admin);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((auth) -> auth
            .requestMatchers("/error").permitAll()
            .requestMatchers(HttpMethod.GET).permitAll() // Allow all GET requests
            .requestMatchers(HttpMethod.PATCH).authenticated() // Require authentication for PATCH
            .requestMatchers(HttpMethod.PUT).authenticated() // Require authentication for PUT
            .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN") // Require ADMIN role for DELETE
            .anyRequest().authenticated() // Require authentication for all other requests
        )
        .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for testing
        .httpBasic(Customizer.withDefaults()) // Enable HTTP Basic Authentication
        .formLogin(Customizer.withDefaults()); // Enable Form-based Login
    return http.build();
  }
}
