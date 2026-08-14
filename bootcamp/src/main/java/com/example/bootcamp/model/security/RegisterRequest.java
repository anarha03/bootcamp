package com.example.bootcamp.model.security;

import com.example.bootcamp.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    Role role;
    @NotBlank
    String name;

    String subject;
    Integer grade;
    String number;
}