package com.example.bootcamp.entity.security;

import com.example.bootcamp.entity.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    String email;
    String password;
    Role role;
    String name;

    String subject;
    Integer grade;
    String number;
}