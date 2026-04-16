package com.example.bootcamp.mapper;

import com.example.bootcamp.dto.request.TeacherRequestDTO;
import com.example.bootcamp.dto.response.TeacherResponseDTO;
import com.example.bootcamp.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "preparations", ignore = true)
    @Mapping(target = "user.id", ignore = true)
    @Mapping(target = "user.balance", ignore = true)
    Teacher requestToEntity(TeacherRequestDTO teacherRequestDTO);
    TeacherResponseDTO entityToResponse (Teacher teacher);
}
