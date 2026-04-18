package com.studentmanagement.controller;

import com.studentmanagement.model.ClassRoom;
import com.studentmanagement.service.ClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/classes")
public class ClassController {

    private final ClassService service;

    public ClassController(ClassService service) {
        this.service = service;
    }

    @GetMapping
    public String listClasses(Model model) {
        model.addAttribute("classes", service.getAll());
        model.addAttribute("classroom", new ClassRoom()); // Form thêm/sửa
        return "admin/class-list";
    }

    // Hiển thị form sửa → đưa dữ liệu lớp vào form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("classes", service.getAll());
        model.addAttribute("classroom", service.getById(id));
        return "admin/class-list";
    }

    @PostMapping("/save")
    public String saveClass(@ModelAttribute("classroom") ClassRoom classRoom) {
        if (classRoom.getId() != null) {
            service.update(classRoom.getId(), classRoom); // Update nếu có ID
        } else {
            service.create(classRoom);                    // Create nếu không có ID
        }
        return "redirect:/admin/classes";
    }

    @GetMapping("/delete/{id}")
    public String deleteClass(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/classes";
    }
}