package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.response.UserResponseDTO;
import com.example.bootcamp.entity.ChangePasswordRequest;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.exception.types.UserNotFoundException;
import com.example.bootcamp.mapper.UserMapper;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ConcreteTypeMunger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::entityToResponse)
                .toList();
    }

    @Override
    public UserResponseDTO get(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return toResponse(user);
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Köhnə şifrə yanlışdır");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private UserResponseDTO toResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .balance(user.getBalance())
                .build();
    }
}
