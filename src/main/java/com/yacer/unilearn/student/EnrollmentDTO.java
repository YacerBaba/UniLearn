package com.yacer.unilearn.student;


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
    private String level;
}
