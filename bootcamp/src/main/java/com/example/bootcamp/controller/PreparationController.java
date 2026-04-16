package com.example.bootcamp.controller;

import com.example.bootcamp.dto.request.PreparationRequestDTO;
import com.example.bootcamp.dto.response.PreparationResponseDTO;
import com.example.bootcamp.entity.security.CustomUserDetails;
import com.example.bootcamp.entity.Role;
import com.example.bootcamp.service.PreparationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preparations")
public class PreparationController {
    private final PreparationService preparationService;

    public PreparationController( PreparationService preparationService) {
        this.preparationService = preparationService;
    }

    @GetMapping("/mypreparations")
    public List<PreparationResponseDTO> getMyPreparations(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long id = userDetails.getId();
        return preparationService.getMyStudents(id);
    }

    @PostMapping
    public void create(@AuthenticationPrincipal CustomUserDetails userDetails,
                       @RequestBody PreparationRequestDTO preparationRequestDTO){
        if (userDetails.getRole().equals(Role.STUDENT)){
            throw new RuntimeException("You can not create Preparation!!");
        }
        preparationService.create(userDetails.getId(),preparationRequestDTO);
    }
    @DeleteMapping("/{id}")
    public PreparationResponseDTO delete(@AuthenticationPrincipal CustomUserDetails userDetails,
                                         @PathVariable Long id){
        if (userDetails.getRole().equals(Role.STUDENT)){
            throw new RuntimeException("You can not delete Preparation!!");
        }
        return preparationService.delete(userDetails.getId(),id);

    }
}
