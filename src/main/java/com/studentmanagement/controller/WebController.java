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

    @GetMapping("/dashboard")
    public String dashboardRouter(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_ADMIN")) return "redirect:/admin/home";
        if (role.equals("ROLE_TEACHER")) return "redirect:/teacher/home";
        return "redirect:/student/home";
    }

    @GetMapping("/admin/home")
    public String adminHome(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "admin/home";
    }

    @GetMapping("/teacher/home")
    public String teacherHome(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "teacher";
    }

    @GetMapping("/student/home")
    public String studentHome(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "student";
    }
}