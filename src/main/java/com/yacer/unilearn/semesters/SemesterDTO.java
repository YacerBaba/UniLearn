package com.yacer.unilearn.semesters;

import com.yacer.unilearn.modules.ModuleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SemesterDTO {
    private Integer semester_id;
    private String semester_name;
    private List<ModuleDTO> modules;
}
