package com.studentmanagement.service;

import com.studentmanagement.dto.GradeCreateDTO;
import com.studentmanagement.dto.GradeDTO;
import com.studentmanagement.model.Course;
import com.studentmanagement.model.Grade;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.CourseRepository;
import com.studentmanagement.repository.GradeRepository;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    private final GradeRepository repo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public GradeService(GradeRepository repo, StudentRepository studentRepo, CourseRepository courseRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public List<Grade> getAll() {
        return repo.findAll();
    }

    public List<Grade> getByStudent(Long studentId) {
        return repo.findByStudentId(studentId);
    }

    // ĐÃ XÓA: getGradesByStudent(Long studentId) → trùng với getMyGrades(String studentCode)
    // Method đó không được gọi ở bất kỳ đâu trong project

    // Method duy nhất để student xem điểm của mình (dùng studentCode từ Authentication)
    public List<GradeDTO> getMyGrades(String studentCode) {
        List<Grade> grades = repo.findByStudent_StudentCode(studentCode);

        return grades.stream()
                .map(grade -> GradeDTO.builder()
                        .gradeId(grade.getId())
                        .courseName(grade.getCourse().getCourseName())
                        .courseCode(grade.getCourse().getCourseCode())
                        .credit(grade.getCourse().getCredit())
                        .midTerm(grade.getMidTerm())
                        .finalExam(grade.getFinalExam())
                        .total(grade.getTotal())
                        .letterGrade(grade.getLetterGrade())
                        .gpa(grade.getGpa())
                        .build())
                .collect(Collectors.toList());
    }

    public Grade createFromDTO(GradeCreateDTO dto) {
        Student student = studentRepo.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên"));
        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học"));

        Grade grade = new Grade();
        grade.setStudent(student);
        grade.setCourse(course);
        grade.setMidTerm(dto.getMidTerm());
        grade.setFinalExam(dto.getFinalExam());

        return calculateAndSave(grade);
    }

    private Grade calculateAndSave(Grade g) {
        double total = g.getMidTerm() * 0.4 + g.getFinalExam() * 0.6;
        g.setTotal(Math.round(total * 10.0) / 10.0);

        double gpa = total >= 8.5 ? 4.0 : total >= 7.0 ? 3.0 : total >= 5.5 ? 2.0 : total >= 4.0 ? 1.0 : 0.0;
        g.setGpa(gpa);

        String letter = total >= 8.5 ? "A" : total >= 7.0 ? "B" : total >= 5.5 ? "C" : total >= 4.0 ? "D" : "F";
        g.setLetterGrade(letter);

        return repo.save(g);
    }
}