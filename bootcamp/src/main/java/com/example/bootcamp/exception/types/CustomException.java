package com.example.bootcamp.exception.types;

import com.example.bootcamp.exception.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {

    ResponseCode responseCode;
    HttpStatus status;

    public CustomException(ResponseCode responseCode, Object parameter) {
        super(responseCode.getDescription().replace("{}", parameter.toString()));
        this.responseCode = responseCode;
        this.status = HttpStatus.BAD_REQUEST;
    }

    public CustomException(ResponseCode responseCode, Object parameter, HttpStatus status) {
        super(responseCode.getDescription().replace("{}", parameter.toString()));
        this.responseCode = responseCode;
        this.status = status;
    }
    public CustomException(ResponseCode responseCode){
        super(responseCode.getDescription());
        this.status = HttpStatus.BAD_REQUEST;


    }
}