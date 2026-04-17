package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.request.PreparationRequestDTO;
import com.example.bootcamp.dto.response.PreparationResponseDTO;
import com.example.bootcamp.entity.*;
import com.example.bootcamp.mapper.PreparationMapper;
import com.example.bootcamp.repository.PreparationRepository;
import com.example.bootcamp.repository.StudentRepository;
import com.example.bootcamp.repository.TeacherRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.PreparationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PreparationServiceImpl implements PreparationService {
    public final PreparationRepository preparationRepository;
    public final TeacherRepository teacherRepository;
    public final PreparationMapper preparationMapper;
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
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        if (user.getRole().equals(Role.TEACHER)){
        Teacher teacher = teacherRepository.findByUserId(id).orElseThrow(RuntimeException::new);
        List<Preparation> byTeacherId = preparationRepository.findByTeacherId(teacher.getId());
        return byTeacherId.stream().map(preparationMapper::entityToResponse).toList();}
        else {
            Student student = studentRepository.findByUserId(id).orElseThrow(RuntimeException::new);
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
                .orElseThrow(() -> new RuntimeException("Teacher tapılmadı"));

        Preparation preparation = preparationRepository
                .findByTeacherIdAndStudentId(teacher.getId(), studentId)
                .orElseThrow(() -> new RuntimeException("Preparation tapılmadı"));

        PreparationResponseDTO response = preparationMapper.entityToResponse(preparation);
        preparationRepository.delete(preparation);
        return response;
    }
}
