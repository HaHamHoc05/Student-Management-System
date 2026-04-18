package com.studentmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🏫 Lớp học
    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;

    // 📚 Môn học
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // 👨‍🏫 Giảng viên
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    // 📅 Thứ trong tuần (MON, TUE,...)
    private String dayOfWeek;

    // ⏰ giờ học
    private LocalTime startTime;
    private LocalTime endTime;

    // 🏫 phòng học
    private String room;
}