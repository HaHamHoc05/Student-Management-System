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
        return "admin/class-list";
    }

    @PostMapping("/save")
    public String saveClass(@ModelAttribute("classroom") ClassRoom classRoom) {
        service.create(classRoom);
        return "redirect:/admin/classes";
    }

    @GetMapping("/delete/{id}")
    public String deleteClass(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/classes";
    }
}