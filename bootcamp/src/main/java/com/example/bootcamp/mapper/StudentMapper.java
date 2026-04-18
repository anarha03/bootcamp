package com.example.bootcamp.mapper;

import com.example.bootcamp.dto.request.StudentRequestDTO;
import com.example.bootcamp.dto.request.TeacherRequestDTO;
import com.example.bootcamp.dto.response.StudentResponseDTO;
import com.example.bootcamp.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "preparations", ignore = true)
    @Mapping(target = "user.id",ignore=true)
    @Mapping(target="user.balance",ignore=true)
    Student requestToEntity(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO entityToResponse (Student student);
}
