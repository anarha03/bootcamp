package com.example.bootcamp.dto.response;

import com.example.bootcamp.entity.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO {

    Long id;

    String email;

    @Enumerated(EnumType.STRING)
    Role role;

    Double balance;
}
