package com.studentmanagement.controller;

import com.studentmanagement.model.Attendance;
import com.studentmanagement.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService service;

    public AttendanceController(AttendanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Attendance> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Attendance create(@RequestBody Attendance a) {
        return service.create(a);
    }

    @GetMapping("/student/{id}")
    public List<Attendance> getByStudent(@PathVariable Long id) {
        return service.getByStudent(id);
    }

    @GetMapping("/rate/{id}")
    public double getRate(@PathVariable Long id) {
        return service.getRate(id);
    }
}