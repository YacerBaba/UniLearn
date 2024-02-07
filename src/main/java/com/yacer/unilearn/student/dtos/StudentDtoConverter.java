package com.yacer.unilearn.student.dtos;

import com.yacer.unilearn.entities.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentDtoConverter {
    private final EnrollmentDtoConverter converter;

    public StudentDTO convertStudentToDTO(Student student) {
        var enrollmentDto = converter.convertToDTO(student.getCurrentEnrollment());
        return new StudentDTO(
                student.getId(),
                student.getUser().getFirstName(),
                student.getUser().getLastName(),
                student.getUser().getEmail(),
                student.getUser().getImg_url(),
                student.getUser().getBirthday(),
                enrollmentDto
        );
    }

    public List<StudentDTO> convertStudentsToDTOsList(List<Student> students) {
        var studentsDto = new LinkedList<StudentDTO>();
        for (var student : students) {
            studentsDto.add(convertStudentToDTO(student));
        }
        return studentsDto;
    }
}
