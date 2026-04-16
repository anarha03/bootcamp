package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.request.TeacherRequestDTO;
import com.example.bootcamp.dto.response.TeacherResponseDTO;
import com.example.bootcamp.entity.Student;
import com.example.bootcamp.entity.Teacher;
import com.example.bootcamp.mapper.TeacherMapper;
import com.example.bootcamp.repository.TeacherRepository;
import com.example.bootcamp.service.TeacherService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    public final TeacherRepository teacherRepository;
    public final TeacherMapper teacherMapper;
    public final PasswordEncoder passwordEncoder;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<TeacherResponseDTO> getAll() {
        List<Teacher> all = teacherRepository.findAll();
        return all.stream().map(teacherMapper::entityToResponse).toList();
    }

    @Override
    public TeacherResponseDTO get(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(RuntimeException::new);
        return teacherMapper.entityToResponse(teacher);
    }

    @Override
    public void create(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = teacherMapper.requestToEntity(teacherRequestDTO);
        teacher.getUser().setPassword(
                passwordEncoder.encode(teacherRequestDTO.getUser().getPassword())
        );
        teacherRepository.save(teacher);    }

    @Override
    public TeacherResponseDTO update(Long id, TeacherRequestDTO teacherRequestDTO) {
        return null;
    }

    @Override
    public TeacherResponseDTO delete(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(RuntimeException::new);
        teacherRepository.delete(teacher);
        return teacherMapper.entityToResponse(teacher);
    }
}
