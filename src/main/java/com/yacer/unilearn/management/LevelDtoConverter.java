package com.yacer.unilearn.management;

import com.yacer.unilearn.entities.Level;
import com.yacer.unilearn.entities.Speciality;
import com.yacer.unilearn.levels.LevelDTO;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class LevelDtoConverter {
    public LevelDTO convertToDto(Level level) {
        return new LevelDTO(
                level.getId(),
                level.getName()
        );
    }

    public List<LevelDTO> convertLevelsToDTOsList(List<Level> levels) {
        var result = new LinkedList<LevelDTO>();
        for (Level level : levels) {
            result.add(convertToDto(level));
        }
        return result;
    }
}
