package com.example.bootcamp.exception.types;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
