package com.studentmanagement.controller;

import com.studentmanagement.model.Grade;
import com.studentmanagement.service.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService service;

    public GradeController(GradeService service) {
        this.service = service;
    }

    @PostMapping
    public Grade create(@RequestBody Grade grade) {
        return service.create(grade);
    }

    @GetMapping("/student/{studentId}")
    public List<Grade> getByStudent(@PathVariable Long studentId) {
        return service.getByStudent(studentId);
    }

    @GetMapping
    public List<Grade> getAll() {
        return service.getAll();
    }
}