package com.yacer.unilearn.teacher;

import com.yacer.unilearn.auth.pojos.Message;
import com.yacer.unilearn.entities.User;
import com.yacer.unilearn.student.dtos.StudentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers/")
@SecurityRequirement(name = "Jwt Authentication")
@Tag(
        name = "Teacher routes"
)
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("")
    @Operation(
            summary = "Get all teachers",
            description = "Retrieve a list of all teachers."
    )
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }


    @GetMapping("details/")
    @Operation(
            summary = "Get Teacher Profile 'for Teacher'",
            description = "Roles: Teacher"
    )
    public ResponseEntity<TeacherDTO> getTeacherProfile(Authentication authentication) {
        var user = authentication.getPrincipal();
        if (user instanceof User) {
            return ResponseEntity.ok(teacherService.getTeacherByEmail(((User) user).getEmail()));
        }
        throw new IllegalStateException("Invalid User");
    }

    @PostMapping("")
    @Operation(
            summary = "Add a new teacher",
            description = "Create a new teacher based on the provided information. 201 for success, 409 for failure"
    )
    public ResponseEntity<Message> addTeacher(@RequestBody RegisterTeacherRequest request) {
        teacherService.addTeacher(request);
        return buildResponseMessage(HttpStatus.CREATED, request.getEmail() + " is created successfully");
    }

    @PutMapping("")
    @Operation(
            summary = "Update a teacher",
            description = "Update an existing teacher based on the provided information. 200 success | 409,403 and others for failure"
    )
    public ResponseEntity<Message> updateTeacher(@RequestBody UpdateTeacherRequest request) {
        teacherService.updateTeacher(request);
        return buildResponseMessage(HttpStatus.OK, "Teacher updated successfully");
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a teacher",
            description = "Delete an existing teacher based on the provided ID. 200 success , otherwise error"
    )
    public ResponseEntity<Message> deleteTeacher(@PathVariable Integer id) {
        teacherService.deleteTeacher(id);
        return buildResponseMessage(HttpStatus.OK, "Teacher deleted successfully");
    }


    private ResponseEntity<Message> buildResponseMessage(HttpStatus status, String msg) {
        var message = new Message(status, msg);
        return ResponseEntity.status(message.getStatus()).body(message);
    }
}
