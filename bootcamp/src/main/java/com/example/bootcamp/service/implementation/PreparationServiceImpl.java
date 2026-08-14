package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.request.PreparationRequestDTO;
import com.example.bootcamp.dto.response.PreparationResponseDTO;
import com.example.bootcamp.exception.ResponseCode;
import com.example.bootcamp.exception.types.PreparationNotFoundException;
import com.example.bootcamp.exception.types.StudentNotFoundException;
import com.example.bootcamp.exception.types.TeacherNotFoundException;
import com.example.bootcamp.exception.types.UserNotFoundException;
import com.example.bootcamp.mapper.PreparationMapper;
import com.example.bootcamp.model.entity.Preparation;
import com.example.bootcamp.model.entity.Student;
import com.example.bootcamp.model.entity.Teacher;
import com.example.bootcamp.model.entity.User;
import com.example.bootcamp.model.enums.Role;
import com.example.bootcamp.repository.PreparationRepository;
import com.example.bootcamp.repository.StudentRepository;
import com.example.bootcamp.repository.TeacherRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.PreparationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreparationServiceImpl implements PreparationService {
    private final PreparationRepository preparationRepository;
    private final TeacherRepository teacherRepository;
    private final PreparationMapper preparationMapper;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;


    public PreparationServiceImpl(PreparationMapper preparationMapper, PreparationRepository preparationRepository, TeacherRepository teacherRepository, UserRepository userRepository, StudentRepository studentRepository) {
        this.preparationRepository = preparationRepository;
        this.teacherRepository = teacherRepository;
        this.preparationMapper = preparationMapper;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<PreparationResponseDTO> getMyPreparations(Long id) {
        User user = getUserById(id);
        if (user.getRole().equals(Role.TEACHER)) {
            Teacher teacher = getTeacherById(id);
            List<Preparation> byTeacherId = preparationRepository.findByTeacherId(teacher.getId());
            return byTeacherId.stream().map(preparationMapper::entityToResponse).toList();
        } else {
            Student student = getStudentById(id);
            List<Preparation> byStudentId = preparationRepository.findByStudentId((student.getId()));
            return byStudentId.stream().map(preparationMapper::entityToResponse).toList();
        }
    }

    @Override
    public void create(PreparationRequestDTO preparationRequestDTO) {
        preparationRepository.save(preparationMapper.requestToEntity(preparationRequestDTO));
    }

    @Override
    public PreparationResponseDTO delete(Long teacherUserId, Long studentId) {

        Teacher teacher = teacherRepository.findByUserId(teacherUserId)
                .orElseThrow(() -> new TeacherNotFoundException(ResponseCode.TEACHER_NOT_FOUND, teacherUserId, HttpStatus.NOT_FOUND));


        Preparation preparation = preparationRepository
                .findByTeacherIdAndStudentId(teacher.getId(), studentId)
                .orElseThrow(() -> new PreparationNotFoundException(ResponseCode.PREPARATION_IS_NOT_FOUND, String.format("%s teacher and %s student ids", teacher.getId(), studentId), HttpStatus.NOT_FOUND));

        PreparationResponseDTO response = preparationMapper.entityToResponse(preparation);
        preparationRepository.delete(preparation);
        return response;
    }

    private Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(ResponseCode.STUDENT_NOT_FOUND, id, HttpStatus.NOT_FOUND));
    }

    private Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(ResponseCode.TEACHER_NOT_FOUND, id, HttpStatus.NOT_FOUND));
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ResponseCode.USER_IS_NOT_FOUND, id, HttpStatus.NOT_FOUND));
    }
}
