package com.example.bootcamp.service.implementation;

import com.example.bootcamp.exception.ResponseCode;
import com.example.bootcamp.exception.types.CustomException;
import com.example.bootcamp.exception.types.UserNotFoundException;
import com.example.bootcamp.model.entity.Student;
import com.example.bootcamp.model.entity.Teacher;
import com.example.bootcamp.model.entity.User;
import com.example.bootcamp.model.enums.Role;
import com.example.bootcamp.model.security.AuthResponse;
import com.example.bootcamp.model.security.JwtUtil;
import com.example.bootcamp.model.security.LoginRequest;
import com.example.bootcamp.model.security.RegisterRequest;
import com.example.bootcamp.repository.StudentRepository;
import com.example.bootcamp.repository.TeacherRepository;
import com.example.bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Bu email artıq qeydiyyatdadır");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);

        if (request.getRole() == Role.STUDENT) {
            Student student = Student.builder()
                    .name(request.getName())
                    .grade(request.getGrade())
                    .number(request.getNumber())
                    .user(user)
                    .build();
            studentRepository.save(student);

        } else if (request.getRole() == Role.TEACHER) {
            Teacher teacher = Teacher.builder()
                    .name(request.getName())
                    .subject(request.getSubject())
                    .user(user)
                    .build();
            teacherRepository.save(teacher);
        }

        String token = jwtUtil.generateToken(user.getId(), user.getEmail());
        return new AuthResponse(token, user.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(ResponseCode.USER_IS_NOT_FOUND,request.getEmail(), HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ResponseCode.PASSWORD_IS_NOT_CORRECT);
        }

        String token = jwtUtil.generateToken(user.getId(), user.getEmail());
        return new AuthResponse(token, user.getRole().name());
    }
}