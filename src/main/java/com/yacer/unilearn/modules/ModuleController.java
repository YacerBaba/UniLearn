package com.yacer.unilearn.modules;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/modules/")
public class ModuleController {
    private final ModuleService service;

    @GetMapping("")
    @Operation(
            summary = "Get list of modules",
            description = "List all modules , ROLE : ADMIN"
    )
    public List<ModuleDTO> getAllModules() {
        return service.getAllModules();
    }

}
