package com.yacer.unilearn.teacher;

import com.yacer.unilearn.enums.Degree;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterTeacherRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthday;
    private Degree degree;
}
