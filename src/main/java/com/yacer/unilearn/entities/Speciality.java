package com.yacer.unilearn.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "specialities")
@Data
public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(
            name = "department_id"
    )
    private Department department;

    @OneToMany(mappedBy = "speciality")
    private List<Level> levels;

}
