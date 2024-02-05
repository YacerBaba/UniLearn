package com.yacer.unilearn.student.services;

import com.yacer.unilearn.auth.services.UserService;
import com.yacer.unilearn.entities.Enrollment;
import com.yacer.unilearn.entities.Student;
import com.yacer.unilearn.levels.LevelRepository;
import com.yacer.unilearn.student.dtos.RegisterStudentRequest;
import com.yacer.unilearn.student.dtos.StudentDTO;
import com.yacer.unilearn.student.dtos.StudentDtoConverter;
import com.yacer.unilearn.student.dtos.UpdateStudentRequest;
import com.yacer.unilearn.student.repositories.AcademicYearRepository;
import com.yacer.unilearn.student.repositories.EnrollmentRepository;
import com.yacer.unilearn.student.repositories.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final LevelRepository levelRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AcademicYearRepository yearRepository;
    private final UserService userService;
    private final StudentDtoConverter converter;

    public List<StudentDTO> getAllStudents() {
        var students = studentRepository.findAll();
        return converter.convertStudentsToDTOsList(students);
    }

    public List<StudentDTO> findStudentsByLevel(String level_name) {
        var level = levelRepository.findLevelByName(level_name).orElse(null);
        if (level == null) return Collections.emptyList();
        var students = studentRepository.findStudentsByCurrentLevel(level);
        return converter.convertStudentsToDTOsList(students);
    }

    @Transactional
    public void createStudent(RegisterStudentRequest request) {
        var user = userService.createUser(request.getFirstName(), request.getLastName(),
                request.getEmail(), request.getPassword(), request.getBirthday(), "STUDENT");
        var student = Student.builder()
                .user(user)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
        studentRepository.save(student);
        var level = levelRepository.findLevelByName(request.getLevel())
                .orElseThrow(() -> new EntityNotFoundException("No such level : " + request.getLevel()));
        var currentAcademicYear = yearRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("No such year with id : 1"));

        var enrollment = Enrollment.builder()
                .student(student)
                .level(level)
                .academicYear(currentAcademicYear)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
        student.setCurrentEnrollment(enrollment);
        studentRepository.save(student);
        enrollmentRepository.save(enrollment);
    }

    public StudentDTO getStudentById(Integer id) {
        var student = studentRepository.findById(id).get();
        var studentDTO = converter.convertStudentToDTO(student);
        return studentDTO;
    }

    @Transactional
    public void updateStudent(UpdateStudentRequest request) {
        var student = studentRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("No such student with id : " + request.getId()));
        student.getUser().setFirstName(request.getFirstName());
        student.getUser().setLastName(request.getLastName());
        student.getUser().setEmail(request.getEmail());
        student.getUser().setBirthday(request.getBirthday());
        var level = levelRepository.findLevelByName(request.getLevel())
                .orElseThrow(() -> new EntityNotFoundException("No such level with id : " + request.getId()));
        student.getCurrentEnrollment().setLevel(level);
        studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such student with id : " + id));
        student.getUser().setAccountNonLocked(false);
        studentRepository.save(student);
    }
}
