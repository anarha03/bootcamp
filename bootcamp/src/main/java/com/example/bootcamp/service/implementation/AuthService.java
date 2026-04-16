package com.example.bootcamp.service.implementation;

import com.example.bootcamp.entity.*;
import com.example.bootcamp.entity.security.AuthResponse;
import com.example.bootcamp.entity.security.JwtUtil;
import com.example.bootcamp.entity.security.LoginRequest;
import com.example.bootcamp.entity.security.RegisterRequest;
import com.example.bootcamp.repository.StudentRepository;
import com.example.bootcamp.repository.TeacherRepository;
import com.example.bootcamp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public final UserRepository userRepository;
    public final StudentRepository studentRepository;
    public final TeacherRepository teacherRepository;
    public final PasswordEncoder passwordEncoder;
    public final JwtUtil jwtUtil;

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
                .orElseThrow(() -> new RuntimeException("Email və ya şifrə yanlışdır"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Email və ya şifrə yanlışdır");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getEmail());
        return new AuthResponse(token, user.getRole().name());
    }
}