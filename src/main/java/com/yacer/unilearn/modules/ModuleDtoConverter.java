package com.yacer.unilearn.modules;

import com.yacer.unilearn.entities.Module;
import com.yacer.unilearn.teacher.TeacherDTO;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class ModuleDtoConverter {

    public ModuleDTO convertToDTO(Module module) {
        return new ModuleDTO(
                module.getId(),
                module.getModuleName() + " - " + module.getSemester().getLevel().getSpeciality().getName(),
                module.getBg_color()
        );
    }

    public List<ModuleDTO> convertModulesToDTOsList(List<Module> modules) {
        var modulesDTO = new LinkedList<ModuleDTO>();
        for (var module : modules) {
            modulesDTO.add(convertToDTO(module));
        }
        return modulesDTO;

    }

}
