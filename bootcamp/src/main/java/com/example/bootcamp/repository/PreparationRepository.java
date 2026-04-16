package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Preparation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PreparationRepository extends JpaRepository<Preparation, Long> {
    List<Preparation> findByTeacherId(Long teacherId);

    Optional<Preparation> findByTeacherIdAndStudentId(Long teacherId, Long studentId);
}
