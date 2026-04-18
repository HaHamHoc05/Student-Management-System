package com.studentmanagement.service;

import com.studentmanagement.model.Course;
import com.studentmanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    // Lấy tất cả môn học (Dùng để hiển thị lên bảng hoặc dropdown)
    public List<Course> getAll() {
        return repo.findAll();
    }

    // Lấy môn học theo ID
    public Course getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học với ID: " + id));
    }

    // Tạo môn học mới
    public Course create(Course course) {
        return repo.save(course);
    }

    // Cập nhật thông tin môn học
    public Course update(Long id, Course newCourse) {
        Course course = getById(id);
        course.setCourseCode(newCourse.getCourseCode());
        course.setCourseName(newCourse.getCourseName());
        course.setCredit(newCourse.getCredit());
        return repo.save(course);
    }

    // Xóa môn học
    public void delete(Long id) {
        repo.deleteById(id);
    }
}