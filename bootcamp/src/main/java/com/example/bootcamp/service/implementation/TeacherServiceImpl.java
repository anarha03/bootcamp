package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.request.TeacherRequestDTO;
import com.example.bootcamp.dto.response.TeacherResponseDTO;
import com.example.bootcamp.exception.types.TeacherNotFoundException;
import com.example.bootcamp.mapper.TeacherMapper;
import com.example.bootcamp.model.entity.Teacher;
import com.example.bootcamp.repository.TeacherRepository;
import com.example.bootcamp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bootcamp.exception.ResponseCode.TEACHER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;


    @Override
    public List<TeacherResponseDTO> getAll() {
        List<Teacher> all = teacherRepository.findAll();
        return all.stream().map(teacherMapper::entityToResponse).toList();
    }

    @Override
    public TeacherResponseDTO get(Long id) {
        Teacher teacher = getById(id);
        return teacherMapper.entityToResponse(teacher);
    }

    @Override
    public void create(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = teacherMapper.requestToEntity(teacherRequestDTO);
        teacher.getUser().setPassword(
                passwordEncoder.encode(teacherRequestDTO.getUser().getPassword())
        );
        teacherRepository.save(teacher);
    }

    @Override
    public TeacherResponseDTO update(Long id, TeacherRequestDTO dto) {
        Teacher teacher = getById(id);

        teacher.setName(dto.getName());
        teacher.setSubject(dto.getSubject());
        teacher.setPhoneNumber(dto.getPhoneNumber());
        teacher.setBio(dto.getBio());

        if (dto.getUser() != null && dto.getUser().getPassword() != null) {
            teacher.getUser().setPassword(
                    passwordEncoder.encode(dto.getUser().getPassword())
            );
        }

        teacherRepository.save(teacher);
        return teacherMapper.entityToResponse(teacher);
    }

    @Override
    public TeacherResponseDTO delete(Long id) {
        Teacher teacher = getById(id);
        teacherRepository.delete(teacher);
        return teacherMapper.entityToResponse(teacher);
    }

    private Teacher getById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(
                        TEACHER_NOT_FOUND, id, HttpStatus.NOT_FOUND));
    }
}
