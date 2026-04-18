package com.studentmanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeDTO {

    private Long gradeId;
    private String courseName;
    private String courseCode;
    private int credit;

    private double midTerm;
    private double finalExam;
    private double total;
    private String letterGrade;
    private double gpa;
}