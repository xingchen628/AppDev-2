package com.example.groupproject.exceptions;

import com.example.groupproject.exceptions.InvalidRequestException;
import com.example.groupproject.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Handle ResourceNotFoundException
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  // Handle InvalidRequestException
  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<String> handleInvalidRequest(InvalidRequestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  // Handle validation errors
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      errors.put(fieldError.getField(), fieldError.getDefaultMessage());
    }
    return ResponseEntity.badRequest().body(errors);
  }

  // Handle generic exceptions
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An unexpected error occurred: " + ex.getMessage());
  }
}
