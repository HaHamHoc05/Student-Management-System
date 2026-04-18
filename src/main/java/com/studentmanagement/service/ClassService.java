package com.studentmanagement.service;

import com.studentmanagement.model.ClassRoom;
import com.studentmanagement.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {

    private final ClassRepository repo;

    public ClassService(ClassRepository repo) {
        this.repo = repo;
    }

    public List<ClassRoom> getAll() {
        return repo.findAll();
    }

    // THÊM MỚI: cần thiết cho edit form
    public ClassRoom getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy lớp học: " + id));
    }

    public ClassRoom create(ClassRoom c) {
        return repo.save(c);
    }

    public ClassRoom update(Long id, ClassRoom newClass) {
        ClassRoom c = repo.findById(id).orElseThrow();
        c.setClassName(newClass.getClassName());
        return repo.save(c);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}