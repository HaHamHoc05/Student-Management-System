package com.studentmanagement.controller;

import com.studentmanagement.model.Schedule;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.AttendanceService;
import com.studentmanagement.service.GradeService;
import com.studentmanagement.service.ScheduleService;
import com.studentmanagement.service.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentPortalController {

    private final GradeService gradeService;
    private final ScheduleService scheduleService;
    private final AttendanceService attendanceService;
    private final StudentService studentService;

    public StudentPortalController(GradeService gradeService, ScheduleService scheduleService,
                                   AttendanceService attendanceService, StudentService studentService) {
        this.gradeService = gradeService;
        this.scheduleService = scheduleService;
        this.attendanceService = attendanceService;
        this.studentService = studentService;
    }

    // Xem điểm của sinh viên đang đăng nhập
    @GetMapping("/my-grades")
    public String viewMyGrades(Model model, Authentication authentication) {
        String studentCode = authentication.getName();
        model.addAttribute("grades", gradeService.getMyGrades(studentCode));
        return "student/my-grade";
    }

    /**
     * THÊM MỚI: Xem điểm danh cá nhân (đã sửa: filter theo đúng sinh viên)
     * BUG CŨ: dùng attendanceService.getAll() → hiện điểm danh của TẤT CẢ sinh viên
     */
    @GetMapping("/my-attendance")
    public String viewMyAttendance(Model model, Authentication authentication) {
        String studentCode = authentication.getName();
        Student student = studentService.getByCode(studentCode);

        var attendances = attendanceService.getByStudentId(student.getId());
        double percent = attendanceService.getAttendancePercent(student.getId());

        model.addAttribute("attendances", attendances);
        model.addAttribute("attendancePercent", percent);
        return "student/my-attendance"; // template mới - đã được tạo
    }

    // Xem lịch học theo lớp của sinh viên
    @GetMapping("/my-schedule")
    public String viewMySchedule(Model model, Authentication auth) {
        String studentCode = auth.getName();
        Student student = studentService.getByCode(studentCode);
        List<Schedule> mySchedules = scheduleService.getSchedulesByClass(student.getClassRoom().getId());
        model.addAttribute("schedules", mySchedules);
        return "student/my-schedule";
    }

    // THÊM MỚI: Xem thông tin cá nhân
    @GetMapping("/my-profile")
    public String viewMyProfile(Model model, Principal principal) {
        String studentCode = principal.getName();
        Student student = studentService.getByCode(studentCode);
        model.addAttribute("student", student);
        return "student/my-profile";
    }

    // Dashboard tổng quan sinh viên
    @GetMapping("/dashboard")
    public String studentDashboard(Model model, Principal principal) {
        String studentCode = principal.getName();
        Student student = studentService.getByCode(studentCode);

        model.addAttribute("student", student);
        model.addAttribute("weeklySchedules",
                scheduleService.getSchedulesByClass(student.getClassRoom().getId()));
        model.addAttribute("grades", gradeService.getMyGrades(studentCode));
        model.addAttribute("attendancePercent",
                attendanceService.getAttendancePercent(student.getId()));
        return "student/dashboard"; // template mới
    }
}