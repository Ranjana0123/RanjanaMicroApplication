package com.microservice.department.entity;

public class InvalidDataExceptionImpl extends RuntimeException {
    public InvalidDataExceptionImpl(String message) {
        super(message);
    }
}
