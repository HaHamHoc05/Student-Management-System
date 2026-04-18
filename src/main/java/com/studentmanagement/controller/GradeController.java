package com.studentmanagement.controller;

import com.studentmanagement.dto.GradeCreateDTO;
import com.studentmanagement.service.CourseService;
import com.studentmanagement.service.GradeService;
import com.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher/grades")
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;
    private final CourseService courseService;

    public GradeController(GradeService gradeService, StudentService studentService, CourseService courseService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public String listAllGrades(Model model) {
        model.addAttribute("grades", gradeService.getAll());
        return "teacher/grade-list";
    }

    @GetMapping("/add")
    public String showGradeForm(Model model) {
        model.addAttribute("gradeDTO", new GradeCreateDTO());
        model.addAttribute("students", studentService.getAll());
        model.addAttribute("courses", courseService.getAll());
        return "teacher/grade-form";
    }

    @PostMapping("/save")
    public String saveGrade(@ModelAttribute("gradeDTO") GradeCreateDTO dto) {
        gradeService.createFromDTO(dto);
        return "redirect:/teacher/grades";
    }
}