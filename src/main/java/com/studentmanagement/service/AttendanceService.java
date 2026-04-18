package com.studentmanagement.service;

import com.studentmanagement.model.Attendance;
import com.studentmanagement.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository repo;

    public AttendanceService(AttendanceRepository repo) {
        this.repo = repo;
    }

    public List<Attendance> getAll() {
        return repo.findAll();
    }

    public List<Attendance> getByStudentId(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    public Attendance create(Attendance a) {
        if (a.getStatus() == null) {
            a.setStatus("PRESENT");
        }
        return repo.save(a);
    }

    public long countByStatus(Long studentId, Long courseId, String status) {
        return repo.findByStudentIdAndCourseId(studentId, courseId).stream()
                .filter(a -> a.getStatus().equals(status))
                .count();
    }

    public double getAttendancePercent(Long studentId) {
        List<Attendance> all = repo.findByStudentId(studentId);
        if (all.isEmpty()) return 100.0;
        long present = all.stream().filter(a -> "PRESENT".equals(a.getStatus())).count();
        return Math.round((present * 100.0 / all.size()) * 10.0) / 10.0;
    }
}