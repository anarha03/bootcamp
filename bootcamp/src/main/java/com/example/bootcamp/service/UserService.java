package com.example.bootcamp.service;

import com.example.bootcamp.dto.response.UserResponseDTO;
import com.example.bootcamp.entity.ChangePasswordRequest;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAll();

    UserResponseDTO get(Long id);

    void changePassword(Long id, ChangePasswordRequest request);
}
