package com.yacer.unilearn.management;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/departments/")
@SecurityRequirement(name = "Jwt Authentication")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("")
    @Operation(
            summary = "Get Department with its specialities and levels"
    )
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

}
