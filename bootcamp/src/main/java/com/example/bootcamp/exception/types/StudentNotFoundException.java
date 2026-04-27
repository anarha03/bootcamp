package com.example.bootcamp.exception.types;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
