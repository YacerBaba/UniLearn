package com.yacer.unilearn.student.dtos;

import com.yacer.unilearn.entities.Enrollment;

public class EnrollmentDtoConverter {

    public EnrollmentDTO convertToDTO(Enrollment enrollment) {
        return new EnrollmentDTO(enrollment.getEnrollmentId(),
                enrollment.getAcademicYear().getStart_date(),
                enrollment.getAcademicYear().getEnd_date(),
                enrollment.getLevel().getName(),
                enrollment.getLevel().getSpeciality().getName());
    }

}
