package com.example.bootcamp.dto.response;

import com.example.bootcamp.entity.PreparationStatus;
import com.example.bootcamp.entity.Student;
import com.example.bootcamp.entity.Teacher;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
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
