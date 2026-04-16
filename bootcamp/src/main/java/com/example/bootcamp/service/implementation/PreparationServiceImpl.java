package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.request.PreparationRequestDTO;
import com.example.bootcamp.dto.response.PreparationResponseDTO;
import com.example.bootcamp.entity.Preparation;
import com.example.bootcamp.entity.Teacher;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.mapper.PreparationMapper;
import com.example.bootcamp.repository.PreparationRepository;
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


    public PreparationServiceImpl(PreparationMapper preparationMapper, PreparationRepository preparationRepository, TeacherRepository teacherRepository, UserRepository userRepository) {
        this.preparationRepository = preparationRepository;
        this.teacherRepository = teacherRepository;
        this.preparationMapper = preparationMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<PreparationResponseDTO> getMyStudents(Long id) {
        Teacher teacher = teacherRepository.findByUserId(id).orElseThrow(RuntimeException::new);
        List<Preparation> byTeacherId = preparationRepository.findByTeacherId(teacher.getId());
        return byTeacherId.stream().map(preparationMapper::entityToResponse).toList();
    }

    @Override
    public void create(Long id, PreparationRequestDTO preparationRequestDTO) {
        preparationRepository.save(preparationMapper.requestToEntity(preparationRequestDTO));
    }

    @Override
    public PreparationResponseDTO delete(Long teacherUserId, Long studentId) {

        // Əvvəlcə User ID-dən Teacher-i tap
        Teacher teacher = teacherRepository.findByUserId(teacherUserId)
                .orElseThrow(() -> new RuntimeException("Teacher tapılmadı"));

        // İndi teacher.getId() — artıq Teacher entity ID-sidir
        Preparation preparation = preparationRepository
                .findByTeacherIdAndStudentId(teacher.getId(), studentId)
                .orElseThrow(() -> new RuntimeException("Preparation tapılmadı"));

        PreparationResponseDTO response = preparationMapper.entityToResponse(preparation);
        preparationRepository.delete(preparation); // SİL
        return response;
    }
}
