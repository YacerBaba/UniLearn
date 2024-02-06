package com.yacer.unilearn.management;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentDtoConverter converter;
    private final DepartmentRepository repository;

    public List<DepartmentDTO> getAllDepartments() {
        var departments = repository.findAll();
        return converter.convertDepartmentsToDTOsList(departments);
    }

}
