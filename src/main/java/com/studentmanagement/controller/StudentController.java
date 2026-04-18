package com.studentmanagement.controller;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.ClassService;
import com.studentmanagement.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/students") // Chỉ Admin mới được vào đây
public class StudentController {

    private final StudentService service;
    private final ClassService classService;

    public StudentController(StudentService service, ClassService classService) {
        this.service = service;
        this.classService = classService;
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

    // Xử lý lưu sinh viên (Cả thêm và sửa)
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        if (student.getId() != null) {
            service.update(student.getId(), student);
        } else {
            service.create(student);
        }
        return "redirect:/admin/students";
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
}