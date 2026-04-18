package com.studentmanagement.service;

import com.studentmanagement.model.Schedule;
import com.studentmanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository repo;

    public ScheduleService(ScheduleRepository repo) {
        this.repo = repo;
    }

    // 📋 GET ALL
    public List<Schedule> getAll() {
        return repo.findAll();
    }

    // ➕ CREATE
    public Schedule create(Schedule s) {
        return repo.save(s);
    }
}
