package com.example.bootcamp.dto.request;

import com.example.bootcamp.entity.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {
    @NotBlank
    String email;
    @NotBlank
    String password;
    @NotBlank
    Role role;
}
