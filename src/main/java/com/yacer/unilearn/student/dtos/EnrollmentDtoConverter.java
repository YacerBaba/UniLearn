package com.yacer.unilearn.student.dtos;

import com.yacer.unilearn.entities.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentDtoConverter {

    public EnrollmentDTO convertToDTO(Enrollment enrollment) {
        return new EnrollmentDTO(enrollment.getEnrollmentId(),
                enrollment.getAcademicYear().getStart_date(),
                enrollment.getAcademicYear().getEnd_date(),
                enrollment.getLevel().getId(),
                enrollment.getLevel().getName().name(),
                enrollment.getLevel().getSpeciality().getId(),
                enrollment.getLevel().getSpeciality().getName(),
                enrollment.getLevel().getSpeciality().getDepartment().getId(),
                enrollment.getLevel().getSpeciality().getDepartment().getName()
        );
    }

}
