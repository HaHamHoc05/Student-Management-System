package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> getAll() {
        return repo.findAll();
    }

    public Student getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Student create(Student s) {
        return repo.save(s);
    }

    public Student update(Long id, Student newStudent) {
        Student s = repo.findById(id).orElseThrow();

        s.setStudentCode(newStudent.getStudentCode());
        s.setFullName(newStudent.getFullName());
        s.setEmail(newStudent.getEmail());
        s.setPhone(newStudent.getPhone());
        s.setGender(newStudent.getGender());
        s.setDob(newStudent.getDob());
        s.setClassRoom(newStudent.getClassRoom());

        return repo.save(s);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}