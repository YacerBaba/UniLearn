package com.yacer.unilearn.student;

import com.yacer.unilearn.auth.pojos.Message;
import com.yacer.unilearn.entities.AcademicYear;
import com.yacer.unilearn.entities.Student;
import com.yacer.unilearn.enums.AcademicStatus;
import com.yacer.unilearn.utils.ApplicationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/students/")
@SecurityRequirement(name = "Jwt Authentication")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("{level}")
    @Operation(
            summary = "Get all students",
            description = "Role : ADMIN"
    )
    public ResponseEntity<List<StudentDTO>> getStudentsByLevel(@PathVariable String level) {
        return ResponseEntity.ok(studentService.findStudentsByLevel(level));
    }

    @GetMapping("")
    @Operation(
            summary = "Get all students",
            description = "Role : ADMIN"
    )
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        ApplicationUtils.appLogger.info("User Authorities: " + authorities); // User Authorities: [Authority(id=2, authority=TEACHER)]
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("profile/{id}")
    @Operation(
            summary = "Get Student Profile",
            description = "Roles : ADMIN , STUDENT"
    )
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    @Operation(
            summary = "Create new student",
            description = "Roles : ADMIN"
    )
    public ResponseEntity<Message> createStudent(@RequestBody RegisterStudentRequest request) {
        studentService.createStudent(request);
        var message = new Message(HttpStatus.CREATED, request.getEmail() + " student created successfully");
        return ResponseEntity.status(message.getStatus()).body(message);
    }

}
