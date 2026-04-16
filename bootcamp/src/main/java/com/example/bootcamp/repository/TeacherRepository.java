package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
Optional<Teacher> findByUserId(Long userId);
}
