package com.example.groupproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception for invalid requests
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {
  public InvalidRequestException(String message) {
    super(message);
  }
}
