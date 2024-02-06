package com.yacer.unilearn.entities;

import com.yacer.unilearn.enums.LevelEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "levels", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "speciality_id"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private LevelEnum name;
    @ManyToOne
    @JoinColumn(
            name = "speciality_id"
    )
    private Speciality speciality;
    @OneToMany(mappedBy = "level")
    private List<Semester> semesters;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime created_at;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updated_at;
}
