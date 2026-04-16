package com.example.bootcamp.mapper;

import com.example.bootcamp.dto.request.PreparationRequestDTO;
import com.example.bootcamp.dto.response.PreparationResponseDTO;
import com.example.bootcamp.entity.Preparation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PreparationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher.id", source = "teacherId")
    @Mapping(target = "student.id", source = "studentId")
    Preparation requestToEntity(PreparationRequestDTO preparationRequestDTO);

    @Mapping(target = "teacherName", source = "teacher.name")
    @Mapping(target = "studentName", source = "student.name")
    PreparationResponseDTO entityToResponse(Preparation preparation);
}
