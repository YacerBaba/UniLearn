package com.yacer.unilearn.student.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EnrollmentDTO {
    private Integer enrollment_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private Integer level_id;
    private String level;
    private Integer speciality_id;
    private String speciality;
    private Integer department_id;
    private String department;
}
