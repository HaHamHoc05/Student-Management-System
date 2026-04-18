package com.studentmanagement.controller;

import com.studentmanagement.model.Attendance;
import com.studentmanagement.model.Schedule;
import com.studentmanagement.model.Student;
import com.studentmanagement.service.AttendanceService;
import com.studentmanagement.service.CourseService;
import com.studentmanagement.service.ScheduleService;
import com.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teacher/attendance")
public class AttendanceController {

    private final AttendanceService service;
    private final StudentService studentService;
    private final CourseService courseService;
    private final ScheduleService scheduleService; // THÊM MỚI: cần cho save-batch

    public AttendanceController(AttendanceService service,
                                StudentService studentService,
                                CourseService courseService,
                                ScheduleService scheduleService) {
        this.service = service;
        this.studentService = studentService;
        this.courseService = courseService;
        this.scheduleService = scheduleService;
    }

    // Hiển thị lịch sử điểm danh
    @GetMapping
    public String listAttendance(Model model) {
        model.addAttribute("attendances", service.getAll());
        return "teacher/attendance-list";
    }

    // Form điểm danh thủ công (từng sinh viên)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("attendance", new Attendance());
        model.addAttribute("students", studentService.getAll());
        model.addAttribute("courses", courseService.getAll());
        return "teacher/attendance-form";
    }

    // Lưu điểm danh thủ công
    @PostMapping("/save")
    public String saveAttendance(@ModelAttribute("attendance") Attendance attendance) {
        if (attendance.getDate() == null) {
            attendance.setDate(LocalDate.now());
        }
        service.create(attendance);
        return "redirect:/teacher/attendance";
    }

    /**
     * THÊM MỚI: Lưu điểm danh hàng loạt từ form attendance-class.html.
     * Form gửi: scheduleId + status_<studentId> cho mỗi sinh viên.
     * Sinh viên không được chọn (không chọn radio nào) → mặc định là PRESENT.
     */
    @PostMapping("/save-batch")
    public String saveBatch(@RequestParam Long scheduleId,
                            @RequestParam Map<String, String> params) {
        Schedule schedule = scheduleService.getById(scheduleId);
        List<Student> students = studentService.getByClass(schedule.getClassRoom().getId());
        LocalDate date = schedule.getScheduleDate() != null
                ? schedule.getScheduleDate()
                : LocalDate.now();

        for (Student s : students) {
            String statusKey = "status_" + s.getId();
            String status = params.getOrDefault(statusKey, "PRESENT"); // Mặc định: Có mặt

            Attendance a = new Attendance();
            a.setStudent(s);
            a.setCourse(schedule.getCourse());
            a.setDate(date);
            a.setStatus(status);
            service.create(a);
        }

        return "redirect:/teacher/attendance";
    }
}