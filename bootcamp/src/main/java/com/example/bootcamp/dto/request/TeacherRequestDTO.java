package com.example.bootcamp.dto.request;

import com.example.bootcamp.dto.response.StudentResponseDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherRequestDTO {
    @NotBlank
    String name;
    String subject;
    String phoneNumber;
    String bio;
    UserRequestDTO user;
}
