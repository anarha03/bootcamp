package com.example.bootcamp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;

@Entity
@Data
public class Preparation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;

    Double price;

    @Enumerated(EnumType.STRING)
    PreparationStatus status;

    @Enumerated(EnumType.STRING)
    List<DayOfWeek> dayOfWeeks;
}
