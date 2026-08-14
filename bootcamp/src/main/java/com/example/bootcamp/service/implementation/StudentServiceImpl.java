package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.request.StudentRequestDTO;
import com.example.bootcamp.dto.response.StudentResponseDTO;
import com.example.bootcamp.exception.ResponseCode;
import com.example.bootcamp.exception.types.StudentNotFoundException;
import com.example.bootcamp.mapper.StudentMapper;
import com.example.bootcamp.model.entity.Student;
import com.example.bootcamp.repository.StudentRepository;
import com.example.bootcamp.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;

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
        Student student = getById(id);
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
    public StudentResponseDTO update(Long id, StudentRequestDTO dto) {
        Student student = getById(id);

        student.setName(dto.getName());
        student.setGrade(dto.getGrade());
        student.setNumber(dto.getNumber());

        if (dto.getUser() != null && dto.getUser().getPassword() != null) {
            student.getUser().setPassword(
                    passwordEncoder.encode(dto.getUser().getPassword())
            );
        }

        studentRepository.save(student);
        return studentMapper.entityToResponse(student);
    }

    @Override
    public StudentResponseDTO delete(Long id) {
        Student student = getById(id);
        studentRepository.delete(student);
        return studentMapper.entityToResponse(student);
    }

    public Student getById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(ResponseCode.STUDENT_NOT_FOUND, id, HttpStatus.NOT_FOUND));
    }
}
