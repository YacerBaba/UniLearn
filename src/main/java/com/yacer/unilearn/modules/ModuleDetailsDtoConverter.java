package com.yacer.unilearn.modules;

import com.yacer.unilearn.entities.Module;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ModuleDetailsDtoConverter {
    private final ModuleWeekDtoConverter converter;

    public ModuleDetailsDTO convertToDto(Module module) {
        var moduleWeeksDto = converter.convertModuleWeeksToDTOsList(module.getModuleWeeks());
        return new ModuleDetailsDTO(
                module.getId(),
                module.getModuleName(),
                module.getBg_color(),
                moduleWeeksDto
        );
    }
}
