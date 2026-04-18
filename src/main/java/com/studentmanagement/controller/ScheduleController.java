package com.studentmanagement.controller;

import com.studentmanagement.model.Schedule;
import com.studentmanagement.service.ClassService;
import com.studentmanagement.service.CourseService;
import com.studentmanagement.service.ScheduleService;
import com.studentmanagement.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/schedule")
public class ScheduleController {

    private final ScheduleService service;
    private final ClassService classService;
    private final CourseService courseService;
    private final TeacherService teacherService;

    public ScheduleController(ScheduleService service, ClassService classService,
                              CourseService courseService, TeacherService teacherService) {
        this.service = service;
        this.classService = classService;
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String listSchedule(Model model) {
        model.addAttribute("schedules", service.getAll());
        return "admin/schedule-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("classes", classService.getAll());
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("teachers", teacherService.getAll());
        return "admin/schedule-form";
    }

    @PostMapping("/save")
    public String saveSchedule(@ModelAttribute("schedule") Schedule schedule) {
        service.create(schedule);
        return "redirect:/admin/schedule";
    }

    // THÊM MỚI: xóa lịch học
    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/schedule";
    }
}