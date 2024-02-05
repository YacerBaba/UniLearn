package com.yacer.unilearn.student.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateStudentRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private String level;
}
