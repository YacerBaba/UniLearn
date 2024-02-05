package com.yacer.unilearn.student;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StudentDTO {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String img_url;
    private LocalDateTime birthday;
    private EnrollmentDTO current_enrollment;
}
