package com.example.bootcamp.service;

import com.example.bootcamp.dto.request.StudentRequestDTO;
import com.example.bootcamp.dto.request.TeacherRequestDTO;
import com.example.bootcamp.dto.response.StudentResponseDTO;
import com.example.bootcamp.dto.response.TeacherResponseDTO;

import java.util.List;

public interface TeacherService {
    List<TeacherResponseDTO> getAll();

    TeacherResponseDTO get(Long id);

    void create(TeacherRequestDTO teacherRequestDTO);

    TeacherResponseDTO update(Long id, TeacherRequestDTO teacherRequestDTO);

    TeacherResponseDTO delete(Long id);
}
