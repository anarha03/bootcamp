package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.request.StudentRequestDTO;
import com.example.bootcamp.dto.response.StudentResponseDTO;
import com.example.bootcamp.entity.Student;
import com.example.bootcamp.mapper.StudentMapper;
import com.example.bootcamp.repository.StudentRepository;
import com.example.bootcamp.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    public final StudentRepository studentRepository;
    public final StudentMapper studentMapper;
    public final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<StudentResponseDTO> getAll() {
        return studentRepository.findAll().stream().map(studentMapper::entityToResponse).toList();
    }

    @Override
    public StudentResponseDTO get(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        return studentMapper.entityToResponse(student);
    }

    @Override
    public void create(StudentRequestDTO dto) {
        Student student = studentMapper.requestToEntity(dto);
        student.getUser().setPassword(
                passwordEncoder.encode(dto.getUser().getPassword())
        );
        studentRepository.save(student);
    }

    @Override
    public StudentResponseDTO update(Long id, StudentRequestDTO studentRequestDTO) {
        return null;
    }

    @Override
    public StudentResponseDTO delete(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        studentRepository.delete(student);
        return studentMapper.entityToResponse(student);
    }
}
