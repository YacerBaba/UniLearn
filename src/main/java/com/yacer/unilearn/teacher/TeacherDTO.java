package com.yacer.unilearn.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class TeacherDTO {
    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private String img_url;
    private LocalDate birthday;
    private String degree;
    private List<TeachesDTO> teaches;
}
