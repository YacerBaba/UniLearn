package com.yacer.unilearn.management;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DepartmentDTO {
    private Integer id;
    private String name;
    private List<SpecialityDTO> specialities;
}
