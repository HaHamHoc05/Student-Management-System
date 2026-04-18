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

    public Attendance create(Attendance a) {
        if (a.getStatus() == null) {
            a.setStatus("PRESENT");
        }
        return repo.save(a);
    }

    public List<Attendance> getByStudent(Long id) {
        return repo.findByStudentId(id);
    }

    public double getRate(Long id) {

        List<Attendance> list = repo.findByStudentId(id);

        if (list.isEmpty()) return 0;

        long present = list.stream()
                .filter(x -> x.getStatus().equals("PRESENT"))
                .count();

        return (present * 100.0) / list.size();
    }
}