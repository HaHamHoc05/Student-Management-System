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
    public Teacher create(Teacher t) { return repo.save(t); }
    public void delete(Long id) { repo.deleteById(id); }
}