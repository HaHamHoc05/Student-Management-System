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
        model.addAttribute("course", new Course()); // Form thêm mới
        return "admin/course-list";
    }

    // Hiển thị form sửa môn học
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("courses", service.getAll());
        model.addAttribute("course", service.getById(id)); // Đổ dữ liệu vào form
        return "admin/course-list";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        if (course.getId() != null) {
            service.update(course.getId(), course); // Cập nhật nếu có ID
        } else {
            service.create(course);                 // Tạo mới nếu không có ID
        }
        return "redirect:/admin/courses";
    }

    // BUG CŨ: tên method là deleteTeacher và redirect về /admin/teachers → đã sửa
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/courses"; // Sửa từ /admin/teachers → /admin/courses
    }
}