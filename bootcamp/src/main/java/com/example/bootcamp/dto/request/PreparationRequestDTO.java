package com.example.bootcamp.dto.request;

import com.example.bootcamp.entity.PreparationStatus;
import com.example.bootcamp.entity.Student;
import com.example.bootcamp.entity.Teacher;
import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;
@Data
public class PreparationRequestDTO {

    Long teacherId;

    Long studentId;

    Double price;

    @Enumerated(EnumType.STRING)
    PreparationStatus status;

    @Enumerated(EnumType.STRING)
    List<DayOfWeek> dayOfWeeks;
}
