package com.yacer.unilearn.semesters;

import com.yacer.unilearn.entities.Semester;
import com.yacer.unilearn.modules.ModuleDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SemesterDtoConverter {

    private final ModuleDtoConverter converter;

    public SemesterDTO convertToDTO(Semester semester) {
        var modules = semester.getModules();
        return new SemesterDTO(
                semester.getId(),
                semester.getName(),
                converter.convertModulesToDTOsList(modules)
        );
    }

    public List<SemesterDTO> convertSemestersToDTOsList(List<Semester> semesters) {
        var semestersDTO = new LinkedList<SemesterDTO>();
        for (var semester : semesters) {
            semestersDTO.add(convertToDTO(semester));
        }
        return semestersDTO;

    }
}
