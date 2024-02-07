package com.yacer.unilearn.modules;

import com.yacer.unilearn.entities.Content;
import com.yacer.unilearn.entities.ModuleWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ModuleWeekDtoConverter {
    private final ContentDtoConverter converter;

    public ModuleWeekDTO convertToDto(ModuleWeek moduleWeek) {
        var contentsDto = converter.convertContentsToDTOsList(moduleWeek.getContents());
        return new ModuleWeekDTO(
                moduleWeek.getModuleWeekId(),
                moduleWeek.getWeek().getWeekName(),
                contentsDto
        );
    }

    public List<ModuleWeekDTO> convertModuleWeeksToDTOsList(List<ModuleWeek> moduleWeeks) {
        var result = new LinkedList<ModuleWeekDTO>();
        for (var moduleWeek : moduleWeeks) {
            result.add(convertToDto(moduleWeek));
        }
        return result;
    }
}
