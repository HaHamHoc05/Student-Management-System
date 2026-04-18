package com.studentmanagement.controller;

import com.studentmanagement.model.Attendance;
import com.studentmanagement.service.AttendanceService;
import com.studentmanagement.service.CourseService;
import com.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher/attendance") // Gắn quyền cho giáo viên
public class AttendanceController {

    private final AttendanceService service;
    private final StudentService studentService;
    private final CourseService courseService;

    public AttendanceController(AttendanceService service, StudentService studentService, CourseService courseService) {
        this.service = service;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    // Hiển thị danh sách điểm danh
    @GetMapping
    public String listAttendance(Model model) {
        model.addAttribute("attendances", service.getAll());
        return "teacher/attendance-list";
    }

    // Giao diện form để điểm danh
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        model.addAttribute("students", studentService.getAll()); // Đổ danh sách SV ra dropdown
        model.addAttribute("courses", courseService.getAll());   // Đổ danh sách môn học ra dropdown
        return "teacher/attendance-form";
    }

    // Lưu thông tin điểm danh
    @PostMapping("/save")
    public String saveAttendance(@ModelAttribute("attendance") Attendance attendance) {
        service.create(attendance);
        return "redirect:/teacher/attendance";
    }
}