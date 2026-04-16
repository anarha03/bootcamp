package com.example.bootcamp.controller;

import com.example.bootcamp.dto.request.StudentRequestDTO;
import com.example.bootcamp.dto.request.TeacherRequestDTO;
import com.example.bootcamp.dto.response.StudentResponseDTO;
import com.example.bootcamp.dto.response.TeacherResponseDTO;
import com.example.bootcamp.service.StudentService;
import com.example.bootcamp.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/teachers")
@RestController
public class TeacherController {
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }
    public final TeacherService teacherService;

    @GetMapping
    List<TeacherResponseDTO> getAll(){
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    TeacherResponseDTO get(@PathVariable Long id){
        return teacherService.get(id);
    }

    @PostMapping
    public void createStd(@RequestBody TeacherRequestDTO teacherRequestDTO){
        teacherService.create(teacherRequestDTO);
    }

    @PutMapping("/{id}")
    TeacherResponseDTO update(@PathVariable Long id,@RequestBody TeacherRequestDTO teacherResponseDTO){
        return teacherService.update(id,teacherResponseDTO);
    }
    @DeleteMapping("/{id}")
    TeacherResponseDTO delete(@PathVariable Long id){
        return teacherService.delete(id);
    }

}
