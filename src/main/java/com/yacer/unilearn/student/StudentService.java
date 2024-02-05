package com.yacer.unilearn.student;

import com.yacer.unilearn.auth.services.UserService;
import com.yacer.unilearn.entities.Enrollment;
import com.yacer.unilearn.entities.Student;
import com.yacer.unilearn.levels.LevelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
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
        var students = studentRepository.findStudentsByCurrentEnrollment_Level(level);
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
                .orElseThrow(() -> new RuntimeException("No such level : " + request.getLevel()));
        var currentAcademicYear = yearRepository.findById(1).get();
        var enrollment = Enrollment.builder()
                .student(student)
                .level(level)
                .academicYear(currentAcademicYear)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
        enrollmentRepository.save(enrollment);
    }

    public StudentDTO getStudentById(Integer id) {
        var student = studentRepository.findById(id).get();
        var studentDTO = converter.convertStudentToDTO(student);
        return studentDTO;
    }
}
