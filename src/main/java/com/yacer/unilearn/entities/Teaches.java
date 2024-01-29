package com.yacer.unilearn.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teacher_module",
        uniqueConstraints = @UniqueConstraint(columnNames = {"teacher_id", "module_id", "enrollment_year"})
)
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Teaches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(
            name = "teacher_id"
    )
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(
            name = "module_id"
    )
    private Module module;
    @ManyToOne
    @JoinColumn(
            name = "academic_year_id"
    )
    private AcademicYear academicYear;
}
