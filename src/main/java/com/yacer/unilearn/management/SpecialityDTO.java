package com.yacer.unilearn.management;

import com.yacer.unilearn.enums.LevelEnum;
import com.yacer.unilearn.levels.LevelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
public class SpecialityDTO {
    private Integer id;
    private String name;
    private List<LevelDTO> levels;
}
