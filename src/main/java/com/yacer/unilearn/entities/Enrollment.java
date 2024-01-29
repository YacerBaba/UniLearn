package com.yacer.unilearn.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.Year;

@Entity
@Table(name = "enrollments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enrollmentId;
    @ManyToOne
    @JoinColumn(
            name = "academic_year_id"
    )
    private AcademicYear academicYear;

    @ManyToOne
    @JoinColumn(
            name = "student_id"
    )
    private Student student;
    @ManyToOne
    @JoinColumn(
            name = "level_id"
    )
    private Level level;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updated_at;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime created_at;
}
