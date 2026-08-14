package com.example.bootcamp.dto.response;

import com.example.bootcamp.model.enums.PreparationStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PreparationResponseDTO {
    Long id;

    String teacherName;

    String studentName;

    Double price;

    @Enumerated(EnumType.STRING)
    PreparationStatus status;

    @Enumerated(EnumType.STRING)
    List<DayOfWeek> dayOfWeeks;
}
