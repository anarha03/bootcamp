package com.example.bootcamp.model.entity;

import com.example.bootcamp.model.enums.PreparationStatus;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.ArrayList;
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

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "preparation_days",
            joinColumns = @JoinColumn(name = "preparation_id")
    )
    @Column(name = "day_of_week")
    List<DayOfWeek> dayOfWeeks = new ArrayList<>();
}
