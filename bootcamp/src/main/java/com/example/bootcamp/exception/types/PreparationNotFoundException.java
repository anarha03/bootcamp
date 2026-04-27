package com.example.bootcamp.exception.types;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PreparationNotFoundException extends RuntimeException {
    public PreparationNotFoundException (String message) {
        super(message);
    }
}
