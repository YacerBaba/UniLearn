package com.yacer.unilearn.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins/")
@SecurityRequirement(name = "Jwt Authentication")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService service;

    @GetMapping("{id}")
    public ResponseEntity<AdminDTO> getAdminProfile(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getAdminProfile(id));
    }
}
