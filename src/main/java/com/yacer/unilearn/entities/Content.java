package com.yacer.unilearn.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "contents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contentId;
    private String content_url;
    @ManyToOne
    @JoinColumn(
            name = "moduleWeek_id"
    )
    private ModuleWeek moduleWeek;
    @ManyToOne
    @JoinColumn(
            name = "teacher_id"
    )
    private Teacher teacher;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateModified;
}


