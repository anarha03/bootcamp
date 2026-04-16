package com.example.bootcamp.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeacherResponseDTO {

    Long id;

    String name;

    String subject;
    String phoneNumber;
    String bio;

}
