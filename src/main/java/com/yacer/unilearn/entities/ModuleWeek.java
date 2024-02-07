package com.yacer.unilearn.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "module_weeks")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ModuleWeek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moduleWeekId;
    @ManyToOne
    @JoinColumn(
            name = "module_id"
    )
    private Module module;

    @ManyToOne
    @JoinColumn(
            name = "week_id"
    )
    private Week week;
    @OneToMany(mappedBy = "moduleWeek")
    private List<Content> contents;
}
