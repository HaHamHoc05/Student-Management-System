package com.studentmanagement.service;

import com.studentmanagement.model.Teacher;
import com.studentmanagement.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository repo;

    public TeacherService(TeacherRepository repo) { this.repo = repo; }

    public List<Teacher> getAll() { return repo.findAll(); }

    // THÊM MỚI
    public Teacher getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên: " + id));
    }

    public Teacher create(Teacher t) { return repo.save(t); }

    public Teacher update(Long id, Teacher newTeacher) {
        Teacher t = getById(id);
        t.setTeacherCode(newTeacher.getTeacherCode());
        t.setFullName(newTeacher.getFullName());
        t.setEmail(newTeacher.getEmail());
        t.setPhone(newTeacher.getPhone());
        t.setSpecialization(newTeacher.getSpecialization());
        return repo.save(t);
    }

    public void delete(Long id) { repo.deleteById(id); }
}