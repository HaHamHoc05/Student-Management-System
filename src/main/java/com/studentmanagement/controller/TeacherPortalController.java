package com.studentmanagement.controller;

import com.studentmanagement.model.Schedule;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.ScheduleService;
import com.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherPortalController {

    private final ScheduleService scheduleService;
    private final StudentService studentService;

    public TeacherPortalController(ScheduleService scheduleService,
                                   StudentService studentService) {
        this.scheduleService = scheduleService;
        this.studentService = studentService;
    }

    // Xem lịch dạy của chính mình
    @GetMapping("/my-schedule")
    public String mySchedule(Model model, Principal principal) {
        String teacherCode = principal.getName();
        model.addAttribute("schedules", scheduleService.getSchedulesByTeacher(teacherCode));
        return "teacher/schedule"; // template: teacher/schedule.html
    }

    // Xem danh sách sinh viên trong lớp để điểm danh
    @GetMapping("/attendance/class/{scheduleId}")
    public String showClassAttendance(@PathVariable Long scheduleId, Model model) {
        Schedule schedule = scheduleService.getById(scheduleId);
        List<Student> students = studentService.getByClass(schedule.getClassRoom().getId());

        model.addAttribute("schedule", schedule);
        model.addAttribute("students", students);
        return "teacher/attendance-class";
    }
}