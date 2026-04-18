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

    public List<Schedule> getAll() {
        return repo.findAll();
    }

    public Schedule create(Schedule s) {
        return repo.save(s);
    }

    public Schedule getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy lịch học!"));
    }

    public List<Schedule> getSchedulesByClass(Long classId) {
        return repo.findByClassRoomId(classId);
    }

    public List<Schedule> getSchedulesByTeacher(String teacherCode) {
        return repo.findByTeacher_TeacherCode(teacherCode);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}