package com.example.bootcamp.exception.types;

import com.example.bootcamp.exception.ResponseCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

public class TeacherNotFoundException extends CustomException {

    public TeacherNotFoundException(ResponseCode responseCode, Object parameter) {
        super(responseCode, parameter);
    }

    public TeacherNotFoundException(ResponseCode responseCode, Object parameter, HttpStatus status) {
        super(responseCode, parameter, status);
    }
}
