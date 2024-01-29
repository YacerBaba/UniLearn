package com.yacer.unilearn.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "academic_years", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"start_date", "end_date"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "start_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate start_date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate end_date;
    @OneToMany(mappedBy = "academicYear")
    private List<Teaches> teachingList;
    @OneToMany(mappedBy = "academicYear")
    private List<Enrollment> enrollments;
}
