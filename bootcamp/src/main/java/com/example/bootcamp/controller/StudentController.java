package com.example.bootcamp.controller;

import com.example.bootcamp.dto.request.StudentRequestDTO;
import com.example.bootcamp.dto.response.StudentResponseDTO;
import com.example.bootcamp.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/students")
@RestController
public class StudentController {
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    public final StudentService studentService;

    @GetMapping
    List<StudentResponseDTO>getAll(){
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    StudentResponseDTO get(@PathVariable Long id){
        return studentService.get(id);
    }

    @PostMapping
    public void createStd(@RequestBody StudentRequestDTO studentRequestDTO){
        studentService.create(studentRequestDTO);
    }

    @PutMapping("/{id}")
    StudentResponseDTO update(@PathVariable Long id,@RequestBody StudentRequestDTO studentRequestDTO){
        return studentService.update(id,studentRequestDTO);
    }
    @DeleteMapping("/{id}")
    StudentResponseDTO delete(@PathVariable Long id){
        return studentService.delete(id);
    }



}
