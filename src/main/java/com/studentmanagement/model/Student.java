package com.studentmanagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentCode;
    private String fullName;
    private String email;
    private String phone;
    private String gender;
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassRoom classRoom;
}