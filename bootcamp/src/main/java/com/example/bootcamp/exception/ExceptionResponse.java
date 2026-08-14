package com.example.bootcamp.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ExceptionResponse {

    private int code;
    private String message;
    private int status;
    private String error;
    private LocalDateTime timestamp;
}