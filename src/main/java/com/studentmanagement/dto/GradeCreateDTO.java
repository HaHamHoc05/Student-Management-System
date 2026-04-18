package com.studentmanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradeCreateDTO {

    private Long studentId;
    private Long courseId;
    private double midTerm;
    private double finalExam;
}