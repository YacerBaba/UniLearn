package com.yacer.unilearn.modules;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModuleDTO {
    private Integer module_id;
    private String module_name;
    private String bg_color;
}
