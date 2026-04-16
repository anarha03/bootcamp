package com.example.bootcamp.service;

import com.example.bootcamp.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAll();

    UserResponseDTO get(Long id);
}
