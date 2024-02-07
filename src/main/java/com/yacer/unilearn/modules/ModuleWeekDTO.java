package com.yacer.unilearn.modules;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ModuleWeekDTO {
    private Integer moduleWeekId;
    private String week_name;
    private List<ContentDTO> contents;
}
