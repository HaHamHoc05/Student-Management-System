package com.studentmanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;
    private String courseName;
    private int credit;
}