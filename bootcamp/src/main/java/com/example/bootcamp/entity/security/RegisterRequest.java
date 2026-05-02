package com.example.bootcamp.entity.security;

import com.example.bootcamp.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @NotBlank
    String email;
    @NotBlank
    String password;
    @NotBlank
    Role role;
    @NotBlank
    String name;

    String subject;
    Integer grade;
    String number;
}