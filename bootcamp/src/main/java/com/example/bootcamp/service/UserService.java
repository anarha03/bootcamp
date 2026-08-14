package com.example.bootcamp.service;

import com.example.bootcamp.dto.response.UserResponseDTO;
import com.example.bootcamp.model.request.ChangePasswordRequest;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAll();

    UserResponseDTO get(Long id);

    void changePassword(Long id, ChangePasswordRequest request);
}
