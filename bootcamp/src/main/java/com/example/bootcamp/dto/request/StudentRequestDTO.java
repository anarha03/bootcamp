package com.example.bootcamp.dto.request;

import com.example.bootcamp.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequestDTO {
    @NotBlank
    String name;
    Integer grade;
    String number;
    UserRequestDTO user;


}
