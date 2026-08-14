package com.example.bootcamp.exception.types;

import com.example.bootcamp.exception.ResponseCode;
import org.springframework.http.HttpStatus;


public class UserNotFoundException extends CustomException {

    public UserNotFoundException(ResponseCode responseCode, Object parameter) {
        super(responseCode, parameter);
    }

    public UserNotFoundException(ResponseCode responseCode, Object parameter, HttpStatus status) {
        super(responseCode, parameter, status);
    }

}
