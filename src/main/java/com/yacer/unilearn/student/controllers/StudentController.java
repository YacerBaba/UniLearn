package com.yacer.unilearn.student.controllers;

import com.yacer.unilearn.auth.pojos.Message;
import com.yacer.unilearn.enums.LevelEnum;
import com.yacer.unilearn.student.dtos.RegisterStudentRequest;
import com.yacer.unilearn.student.dtos.StudentDTO;
import com.yacer.unilearn.student.dtos.UpdateStudentRequest;
import com.yacer.unilearn.student.services.StudentService;
import com.yacer.unilearn.utils.ApplicationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Tag(
        name = "Student routes"
)
public class StudentController {
    private final StudentService studentService;

    @GetMapping("{level}")
    @Operation(
            summary = "Get students by level",
            description = "Role : ADMIN"
    )
    public ResponseEntity<List<StudentDTO>> getStudentsByLevel(@PathVariable LevelEnum level) {
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

    @PutMapping("")
    @Operation(
            summary = "update existing student",
            description = "Roles : ADMIN"
    )
    public ResponseEntity<Message> updateStudent(@RequestBody UpdateStudentRequest request) {
        studentService.updateStudent(request);
        return ResponseEntity.ok(new Message(HttpStatus.OK, "Student updated successfully"));
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete student",
            description = "Roles : ADMIN"
    )
    public ResponseEntity<Message> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(new Message(HttpStatus.OK, "Deleted successfully"));
    }

}
