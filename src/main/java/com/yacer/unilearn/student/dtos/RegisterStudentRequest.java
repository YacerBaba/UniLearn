package com.yacer.unilearn.student.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthday;
    private String level;
}
