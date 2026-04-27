package com.example.bootcamp.service;

import com.example.bootcamp.dto.request.StudentRequestDTO;
import com.example.bootcamp.dto.response.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    List<StudentResponseDTO> getAll();

    StudentResponseDTO get(Long id);

    void create(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO update(Long id, StudentRequestDTO studentRequestDTO);

    StudentResponseDTO delete(Long id);


}
