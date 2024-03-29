package com.yacer.unilearn.levels;

import com.yacer.unilearn.enums.LevelEnum;
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
@RequestMapping("/api/levels/")
@SecurityRequirement(name = "Jwt Authentication")
public class LevelController {

    @GetMapping("")
    @Operation(
            summary = "Get All Levels" ,
            description = "Get All levels , Role ADMIN"
    )
    public ResponseEntity<List<LevelEnum>> getAllLevels() {
        List<LevelEnum> levels = List.of(
                LevelEnum.L1, LevelEnum.L2,
                LevelEnum.L3, LevelEnum.M1, LevelEnum.M2);
        return ResponseEntity.of(Optional.of(levels));
    }
}
