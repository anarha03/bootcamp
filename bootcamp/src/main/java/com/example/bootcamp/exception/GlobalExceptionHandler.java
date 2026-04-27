package com.example.bootcamp.exception;

import com.example.bootcamp.exception.types.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

@ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(TeacherNotFoundException ex, WebRequest request){
log.error("Teacher not found");
final ErrorResponseDTO responseDTO=ErrorResponseDTO.builder()
        .code("EXCEPTION-001")
        .message("Teacher Not Found")
        .time(LocalDateTime.now())
        .status(404)
        .path(((ServletWebRequest)request).getRequest().getRequestURI())
        .build();
return ResponseEntity.status(404).body(responseDTO);
}
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(StudentNotFoundException ex, WebRequest request){
        log.error("Student not found");
        final ErrorResponseDTO responseDTO=ErrorResponseDTO.builder()
                .code("EXCEPTION-002")
                .message("Student Not Found")
                .time(LocalDateTime.now())
                .status(404)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return ResponseEntity.status(404).body(responseDTO);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(UserNotFoundException ex, WebRequest request){
        log.error("User not found");
        final ErrorResponseDTO responseDTO=ErrorResponseDTO.builder()
                .code("EXCEPTION-003")
                .message("User Not Found")
                .time(LocalDateTime.now())
                .status(404)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return ResponseEntity.status(404).body(responseDTO);
    }
    @ExceptionHandler(PreparationNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handle(PreparationNotFoundException ex, WebRequest request){
        log.error("Preparation not found");
        final ErrorResponseDTO responseDTO=ErrorResponseDTO.builder()
                .code("EXCEPTION-004")
                .message("Preparation Not Found")
                .time(LocalDateTime.now())
                .status(404)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return ResponseEntity.status(404).body(responseDTO);
    }
    @ExceptionHandler(HaveNotEnoughBalanceException.class)
    public ResponseEntity<ErrorResponseDTO> handle(HaveNotEnoughBalanceException ex, WebRequest request){
        log.error("Have not enough balance");
        final ErrorResponseDTO responseDTO=ErrorResponseDTO.builder()
                .code("EXCEPTION-005")
                .message("Have not enough balance")
                .time(LocalDateTime.now())
                .status(400)
                .path(((ServletWebRequest)request).getRequest().getRequestURI())
                .build();
        return ResponseEntity.status(400).body(responseDTO);
    }


}