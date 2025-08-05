package com.microservice.department;

import com.microservice.department.entity.InvalidDataExceptionImpl;
import com.microservice.department.entity.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> responseNotFound(ResourceNotFoundException ex) {
        Map<String, Object> objectMap = Map.of("timspan", LocalDate.now(), "status", HttpStatus.NOT_FOUND.value(), "Massage", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(objectMap);
    }

    @ExceptionHandler(InvalidDataExceptionImpl.class)
    public ResponseEntity<Map<String, Object>> handleInvalidData(InvalidDataExceptionImpl ex) {
        Map<String, Object> body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "Bad Request",
                "message", ex.getMessage()
        );
        return ResponseEntity.badRequest().body(body);
    }
}
