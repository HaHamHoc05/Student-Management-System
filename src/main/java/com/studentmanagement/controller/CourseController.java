package com.studentmanagement.controller;

import com.studentmanagement.model.Course;
import com.studentmanagement.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/courses")
public class CourseController {
    private final CourseService service;
    public CourseController(CourseService service) { this.service = service; }

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", service.getAll());
        return "admin/course-list";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        service.create(course);
        return "redirect:/admin/courses";
    }
}