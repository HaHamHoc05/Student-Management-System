package com.studentmanagement.controller;

import com.studentmanagement.model.Teacher;
import com.studentmanagement.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/teachers")
public class TeacherController {
    private final TeacherService service;
    public TeacherController(TeacherService service) { this.service = service; }

    @GetMapping
    public String listTeachers(Model model) {
        model.addAttribute("teachers", service.getAll());
        return "admin/teacher-list";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute Teacher teacher) {
        service.create(teacher);
        return "redirect:/admin/teachers";
    }
}