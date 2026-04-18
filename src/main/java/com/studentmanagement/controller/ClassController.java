package com.studentmanagement.controller;

import com.studentmanagement.model.ClassRoom;
import com.studentmanagement.service.ClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final ClassService service;

    public ClassController(ClassService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClassRoom> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ClassRoom create(@RequestBody ClassRoom c) {
        return service.create(c);
    }

    @PutMapping("/{id}")
    public ClassRoom update(@PathVariable Long id, @RequestBody ClassRoom c) {
        return service.update(id, c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}