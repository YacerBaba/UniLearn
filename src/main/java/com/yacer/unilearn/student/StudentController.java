package com.yacer.unilearn.student;

import com.yacer.unilearn.entities.Student;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students/")
@PreAuthorize("hasAnyAuthority('ADMIN','TEACHER')")
@SecurityRequirement(name = "Jwt Authentication")
public class StudentController {

    @GetMapping("{level}")
    public List<Student> getStudentsByLevel(@PathVariable String level) {
        return List.of();
    }
}
