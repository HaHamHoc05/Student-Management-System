package com.studentmanagement.controller;

import com.studentmanagement.model.Schedule;
import com.studentmanagement.service.ClassService;
import com.studentmanagement.service.CourseService;
import com.studentmanagement.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/schedule") // Gắn quyền cho Admin
public class ScheduleController {

    private final ScheduleService service;
    private final ClassService classService;
    private final CourseService courseService;

    public ScheduleController(ScheduleService service, ClassService classService, CourseService courseService) {
        this.service = service;
        this.classService = classService;
        this.courseService = courseService;
    }

    // Hiển thị lịch học
    @GetMapping
    public String listSchedule(Model model) {
        model.addAttribute("schedules", service.getAll());
        return "admin/schedule-list";
    }

    // Form tạo lịch học mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("classes", classService.getAll()); // Chọn lớp
        model.addAttribute("courses", courseService.getAll()); // Chọn môn

        return "admin/schedule-form";
    }

    // Lưu lịch học
    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule) {
        service.create(schedule);
        return "redirect:/admin/schedule";
    }
}