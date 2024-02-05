package com.yacer.unilearn.teacher;

import com.yacer.unilearn.enums.Degree;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateTeacherRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private Degree degree;
}
