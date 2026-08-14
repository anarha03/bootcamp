package com.example.bootcamp.exception;

import com.example.bootcamp.exception.types.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException ex) {

        log.error("Exception : {}", ex.getMessage());

        ExceptionResponse response = ExceptionResponse.builder()
                .code(ex.getResponseCode().getCode())
                .message(ex.getMessage())
                .status(ex.getStatus().value())
                .error(ex.getStatus().getReasonPhrase())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(ex.getStatus())
                .body(response);
    }
}