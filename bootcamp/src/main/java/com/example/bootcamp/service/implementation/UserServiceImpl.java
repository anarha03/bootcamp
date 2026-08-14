package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.response.UserResponseDTO;
import com.example.bootcamp.exception.ResponseCode;
import com.example.bootcamp.exception.types.CustomException;
import com.example.bootcamp.exception.types.UserNotFoundException;
import com.example.bootcamp.mapper.UserMapper;
import com.example.bootcamp.model.entity.User;
import com.example.bootcamp.model.request.ChangePasswordRequest;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        User user = getUserById(id);
        return toResponse(user);
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequest request) {
        User user = getUserById(userId);

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new CustomException(ResponseCode.PASSWORD_IS_NOT_CORRECT);
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

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ResponseCode.USER_IS_NOT_FOUND, id, HttpStatus.NOT_FOUND));
    }
}
