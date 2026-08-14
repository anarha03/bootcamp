package com.example.bootcamp.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    TEACHER_NOT_FOUND(1, "Teacher '{}' is not found"),
    STUDENT_NOT_FOUND(2, "Teacher '{}' is not found"),
    USER_IS_NOT_FOUND(3,"User: '{}' is not found"),
    PREPARATION_IS_NOT_FOUND(4,"Preparation: '{}' is not found"),
    PASSWORD_IS_NOT_CORRECT(5,"Password is not correct"),
    BALANCE_IS_NOT_ENOUGH(6,"Your balance is not enough. Your balance: '{}'");
    private final int code;
    private final String description;
    }