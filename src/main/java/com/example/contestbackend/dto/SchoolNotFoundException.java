package com.example.contestbackend.dto;

public class SchoolNotFoundException extends Exception {
    public SchoolNotFoundException(String message) {
        super(message);
    }
}