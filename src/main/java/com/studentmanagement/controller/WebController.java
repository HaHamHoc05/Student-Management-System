package com.studentmanagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 1. Điều hướng thông minh sau khi đăng nhập
    @GetMapping("/dashboard")
    public String dashboardRouter(Authentication authentication) {
        // Lấy quyền của người dùng hiện tại
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_ADMIN")) return "redirect:/admin/home";
        if (role.equals("ROLE_TEACHER")) return "redirect:/teacher/home";

        // Mặc định là sinh viên
        return "redirect:/student/home";
    }

    // 2. Giao diện dành cho ADMIN
    @GetMapping("/admin/home")
    public String adminHome(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "admin"; // Trỏ tới file admin.html
    }

    // 3. Giao diện dành cho GIÁO VIÊN
    @GetMapping("/teacher/home")
    public String teacherHome(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "teacher"; // Trỏ tới file teacher.html
    }

    // 4. Giao diện dành cho SINH VIÊN
    @GetMapping("/student/home")
    public String studentHome(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "student"; // Trỏ tới file student.html
    }
}