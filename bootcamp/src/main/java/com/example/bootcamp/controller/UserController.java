package com.example.bootcamp.controller;

import com.example.bootcamp.dto.response.UserResponseDTO;
import com.example.bootcamp.entity.security.CustomUserDetails;
import com.example.bootcamp.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    public final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO>getAll(@AuthenticationPrincipal CustomUserDetails userDetails){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public UserResponseDTO get(@AuthenticationPrincipal CustomUserDetails userDetails,
                               @PathVariable Long id){
        return userService.get(id);
    }

}
