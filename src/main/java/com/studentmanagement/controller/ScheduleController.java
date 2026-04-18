package com.studentmanagement.controller;

import com.studentmanagement.model.Schedule;
import com.studentmanagement.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    // 📋 xem lịch
    @GetMapping
    public List<Schedule> getAll() {
        return service.getAll();
    }

    // ➕ tạo lịch
    @PostMapping
    public Schedule create(@RequestBody Schedule s) {
        return service.create(s);
    }
}