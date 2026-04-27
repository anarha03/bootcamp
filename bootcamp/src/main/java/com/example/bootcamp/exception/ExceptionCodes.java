package com.example.bootcamp.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExceptionCodes {
    TEACHER_NOT_FOUND("EXCPTION-001"),
    STUDENT_NOT_FOUND("EXCEPTION-002"),
    USER_NOT_FOUND("EXCEPTION-003"),
    PREPARATION_NOT_FOUND("EXCEPTION-004"),
    HAVE_NOT_ENOUGH_BALANCE("EXCEPTION-005");

    public final String code;
}