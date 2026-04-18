package com.studentmanagement.controller;

import com.studentmanagement.dto.GradeDTO;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.GradeService;
import com.studentmanagement.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;
    private final GradeService gradeService;

    public StudentController(StudentService service, GradeService gradeService) {
        this.service = service;
        this.gradeService = gradeService;
    }

    // GET ALL
    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // CREATE
    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return service.update(id, student);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // 🎓 Student xem điểm của mình
    @GetMapping("/{studentId}/grades")
    public List<GradeDTO> getStudentGrades(@PathVariable Long studentId) {
        return gradeService.getGradesByStudent(studentId);
    }
}