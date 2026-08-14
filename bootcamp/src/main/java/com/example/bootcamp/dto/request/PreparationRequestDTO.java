package com.example.bootcamp.dto.request;

import com.example.bootcamp.model.enums.PreparationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;

@Data
public class PreparationRequestDTO {
    @NotBlank
    Long teacherId;
    @NotBlank
    Long studentId;

    Double price;

    @Enumerated(EnumType.STRING)
    PreparationStatus status;

    @Enumerated(EnumType.STRING)
    List<DayOfWeek> dayOfWeeks;
}
