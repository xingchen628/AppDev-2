package com.example.groupproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GroupProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(GroupProjectApplication.class, args);
  }

}
