package com.yacer.unilearn.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teacher_module")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
