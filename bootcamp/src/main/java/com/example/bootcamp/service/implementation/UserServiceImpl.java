package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.response.UserResponseDTO;
import com.example.bootcamp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public UserResponseDTO get(Long id) {
        return null;
    }
}
