package com.example.bootcamp.exception.types;

import com.example.bootcamp.exception.ResponseCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

public class HaveNotEnoughBalanceException extends CustomException {
    public HaveNotEnoughBalanceException(ResponseCode responseCode, Object parameter) {
        super(responseCode, parameter);
    }

    public HaveNotEnoughBalanceException(ResponseCode responseCode, Object parameter, HttpStatus status) {
        super(responseCode, parameter, status);
    }
}
