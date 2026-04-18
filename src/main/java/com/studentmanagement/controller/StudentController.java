package com.studentmanagement.controller;

import com.studentmanagement.model.Account;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.AccountRepository;
import com.studentmanagement.service.ClassService;
import com.studentmanagement.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/students") // Chỉ Admin mới được vào đây
public class StudentController {

    private final StudentService service;
    private final ClassService classService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentController(StudentService service, ClassService classService, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.classService = classService;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", service.getAll());
        return "admin/student-list"; // Trỏ tới templates/admin/student-list.html
    }

    // Hiển thị form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("classes", classService.getAll()); // Để chọn lớp
        return "admin/student-form";
    }


    // Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", service.getById(id));
        model.addAttribute("classes", classService.getAll());
        return "admin/student-form";
    }

    // Xóa sinh viên
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/students";
    }



    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        service.create(student);

        // Tự động tạo Account nếu chưa tồn tại
        if (!accountRepository.existsByUsername(student.getStudentCode())) {
            Account acc = new Account();
            acc.setUsername(student.getStudentCode());
            acc.setPassword(passwordEncoder.encode("123456"));
            acc.setRole("ROLE_STUDENT");
            accountRepository.save(acc);
        }
        return "redirect:/admin/students";
    }
}