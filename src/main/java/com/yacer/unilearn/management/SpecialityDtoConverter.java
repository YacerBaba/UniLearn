package com.yacer.unilearn.management;

import com.yacer.unilearn.entities.Speciality;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SpecialityDtoConverter {
    private final LevelDtoConverter levelDtoConverter;

    public SpecialityDTO convertToDto(Speciality speciality) {
        var levelsDtoList = levelDtoConverter.convertLevelsToDTOsList(speciality.getLevels());
        return new SpecialityDTO(
                speciality.getId(),
                speciality.getName(),
                levelsDtoList
        );
    }

    public List<SpecialityDTO> convertSpecialitiesToDTOsList(List<Speciality> specialities) {
        var specialitiesDtoList = new LinkedList<SpecialityDTO>();
        for (var speciality : specialities) {
            specialitiesDtoList.add(convertToDto(speciality));
        }
        return specialitiesDtoList;
    }
}
