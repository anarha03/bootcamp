package com.example.bootcamp.exception.types;

import com.example.bootcamp.exception.ResponseCode;
import org.springframework.http.HttpStatus;

public class PreparationNotFoundException extends CustomException {
    public PreparationNotFoundException(ResponseCode responseCode, Object parameter) {
        super(responseCode, parameter);
    }

    public PreparationNotFoundException(ResponseCode responseCode, Object parameter, HttpStatus status) {
        super(responseCode, parameter, status);
    }
}
