package com.yacer.unilearn.student;

import com.yacer.unilearn.entities.Student;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class StudentDtoConverter {
    public StudentDTO convertStudentToDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getUser().getFirstName(),
                student.getUser().getLastName(),
                student.getUser().getEmail(),
                student.getUser().getImg_url(),
                student.getUser().getBirthday(),
                new EnrollmentDTO(
                        student.getCurrentEnrollment().getEnrollmentId(),
                        student.getCurrentEnrollment().getAcademicYear().getStart_date(),
                        student.getCurrentEnrollment().getAcademicYear().getEnd_date(),
                        student.getCurrentEnrollment().getLevel().getName()
                )
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
