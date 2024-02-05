package com.yacer.unilearn.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins/")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

//    @GetMapping("{id}")
//    public ResponseEntity<AdminDTO> getAdminProfile(@PathVariable Integer id) {
//
//    }
}
