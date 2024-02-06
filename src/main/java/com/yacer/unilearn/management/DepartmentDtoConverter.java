package com.yacer.unilearn.management;

import com.yacer.unilearn.entities.Department;
import com.yacer.unilearn.entities.Level;
import com.yacer.unilearn.levels.LevelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DepartmentDtoConverter {
    private final SpecialityDtoConverter specialityDtoConverter;

    public DepartmentDTO convertDepartmentToDto(Department department) {
        var specialitiesDto = specialityDtoConverter.convertSpecialitiesToDTOsList(department.getSpecialities());
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                specialitiesDto
        );
    }

    public List<DepartmentDTO> convertDepartmentsToDTOsList(List<Department> departments) {
        var departmentsDTOs = new LinkedList<DepartmentDTO>();
        for (var department : departments) {
            departmentsDTOs.add(convertDepartmentToDto(department));
        }
        return departmentsDTOs;
    }

}
