package com.example.bootcamp.exception.types;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HaveNotEnoughBalanceException extends RuntimeException {
    public HaveNotEnoughBalanceException(String message) {
        super(message);
    }
}
