package com.example.bootcamp.service;

import com.example.bootcamp.dto.request.PreparationRequestDTO;
import com.example.bootcamp.dto.response.PreparationResponseDTO;

import java.util.List;

public interface PreparationService {

    List<PreparationResponseDTO>getMyStudents(Long id);

    void create(Long id, PreparationRequestDTO preparationRequestDTO);

    PreparationResponseDTO delete(Long teacherId, Long studentId);
}
