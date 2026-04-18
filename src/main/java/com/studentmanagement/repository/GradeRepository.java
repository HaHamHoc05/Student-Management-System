package com.studentmanagement.repository;

import com.studentmanagement.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findByStudent_StudentCode(String studentCode);
}
