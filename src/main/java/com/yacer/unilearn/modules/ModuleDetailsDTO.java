package com.yacer.unilearn.modules;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ModuleDetailsDTO {
    private Integer module_id;
    private String module_name;
    private String bg_color;
    private List<ModuleWeekDTO> weeks;
}
