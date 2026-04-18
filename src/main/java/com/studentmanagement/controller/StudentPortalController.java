package com.studentmanagement.controller;

import com.studentmanagement.service.GradeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentPortalController {

    private final GradeService gradeService;

    public StudentPortalController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/my-grades")
    public String viewMyGrades(Model model, Authentication authentication) {
        // Lấy mã sinh viên từ username đã đăng nhập
        String studentCode = authentication.getName();
        model.addAttribute("grades", gradeService.getMyGrades(studentCode)); // Sử dụng hàm đã có trong GradeService
        return "student/my-grades"; // Bạn cần tạo file templates/student/my-grades.html
    }
}