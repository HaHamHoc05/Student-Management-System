package com.studentmanagement.controller;

import com.studentmanagement.model.Account;
import com.studentmanagement.model.Teacher;
import com.studentmanagement.repository.AccountRepository;
import com.studentmanagement.service.TeacherService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/teachers")
public class TeacherController {
    private final TeacherService service;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public TeacherController(TeacherService service,
                             AccountRepository accountRepository,
                             PasswordEncoder passwordEncoder) {
        this.service = service;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String listTeachers(Model model) {
        model.addAttribute("teachers", service.getAll());
        model.addAttribute("teacher", new Teacher()); // Form thêm mới
        return "admin/teacher-list";
    }

    // THÊM MỚI: hiển thị form sửa giảng viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("teachers", service.getAll());
        model.addAttribute("teacher", service.getById(id));
        return "admin/teacher-list";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute Teacher teacher) {
        if (teacher.getId() != null) {
            service.update(teacher.getId(), teacher); // Cập nhật nếu có ID
        } else {
            service.create(teacher);
            // Tự động tạo Account khi thêm mới
            if (!accountRepository.existsByUsername(teacher.getTeacherCode())) {
                Account acc = new Account();
                acc.setUsername(teacher.getTeacherCode());
                acc.setPassword(passwordEncoder.encode("123456"));
                acc.setRole("ROLE_TEACHER");
                accountRepository.save(acc);
            }
        }
        return "redirect:/admin/teachers";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/teachers";
    }


}