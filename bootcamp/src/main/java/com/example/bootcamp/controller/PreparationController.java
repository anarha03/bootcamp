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

    public PreparationController(PreparationService preparationService) {
        this.preparationService = preparationService;
    }

    @GetMapping("/myPreparations")
    public List<PreparationResponseDTO> getMyPreparations(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return preparationService.getMyPreparations(userDetails.getId());
    }

    @PostMapping
    public void create(@AuthenticationPrincipal CustomUserDetails userDetails,
                       @RequestBody PreparationRequestDTO preparationRequestDTO) {
        preparationService.create(preparationRequestDTO);
    }

    @DeleteMapping("/{id}")
    public PreparationResponseDTO delete(@AuthenticationPrincipal CustomUserDetails userDetails,
                                         @PathVariable Long id) {

        return preparationService.delete(userDetails.getId(), id);

    }
}
