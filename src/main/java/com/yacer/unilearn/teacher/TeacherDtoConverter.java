package com.yacer.unilearn.teacher;

import com.yacer.unilearn.entities.Teacher;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class TeacherDtoConverter {

    public TeacherDTO convertTeacherToDTO(Teacher teacher) {
        return new TeacherDTO(
                teacher.getId(),
                teacher.getUser().getFirstName(),
                teacher.getUser().getLastName(),
                teacher.getUser().getLastName(),
                teacher.getUser().getEmail(),
                teacher.getUser().getBirthday(),
                teacher.getDegree().name(),
                null
        );
    }

    public List<TeacherDTO> convertTeachersToDTOsList(List<Teacher> teachers) {
        var teachersDTO = new LinkedList<TeacherDTO>();
        for (var teacher : teachers) {
            teachersDTO.add(convertTeacherToDTO(teacher));
        }
        return teachersDTO;
    }
}
