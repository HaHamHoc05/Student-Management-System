package com.studentmanagement.repository;

import com.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentCode(String studentCode);
    List<Student> findByClassRoomId(Long classId);
}
