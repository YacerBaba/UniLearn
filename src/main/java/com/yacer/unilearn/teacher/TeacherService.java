package com.yacer.unilearn.teacher;

import com.yacer.unilearn.auth.services.UserService;
import com.yacer.unilearn.entities.Teacher;
import com.yacer.unilearn.entities.Teaches;
import com.yacer.unilearn.modules.ModuleRepository;
import com.yacer.unilearn.student.repositories.AcademicYearRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeachingRepository teachingRepository;
    private final ModuleRepository moduleRepository;
    private final AcademicYearRepository academicYearRepository;
    private final TeacherDtoConverter converter;
    private final UserService userService;

    public List<TeacherDTO> getAllTeachers() {
        var teachers = teacherRepository.findAll();
        return converter.convertTeachersToDTOsList(teachers);
    }

    public TeacherDTO getTeacherProfile(int id) {
        var teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not such teacher with id :" + id));
        return converter.convertTeacherToDTO(teacher);
    }

    @Transactional
    public void updateTeacher(UpdateTeacherRequest request) {
        var teacher = teacherRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Not such teacher with id :" + request.getId()));
        teacher.getUser().setFirstName(request.getFirstName());
        teacher.getUser().setLastName(request.getLastName());
        teacher.getUser().setEmail(request.getEmail());
        teacher.getUser().setBirthday(request.getBirthday());
        teacher.setDegree(request.getDegree());
        teacher.setUpdated_at(LocalDateTime.now());
        teacherRepository.save(teacher);
    }

    @Transactional
    public void addTeacher(RegisterTeacherRequest request) {
        var user = userService.createUser(request.getFirstName(), request.getLastName(),
                request.getEmail(), request.getPassword(), request.getBirthday(), "TEACHER");
        var teacher = Teacher.builder()
                .user(user)
                .degree(request.getDegree())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
        teacherRepository.save(teacher);
        var module = moduleRepository.findById(request.getModule_id())
                .orElseThrow(() -> new EntityNotFoundException("Not such module with id :" + request.getModule_id()));
        var academicYear = academicYearRepository.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("Not such academic year with id :" + request.getModule_id()));
        var teaches = Teaches.builder()
                .teacher(teacher)
                .module(module)
                .academicYear(academicYear)
                .build();
        teachingRepository.save(teaches);
    }

    public void deleteTeacher(Integer id) {
        var teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not such teacher with id :" + id));
        teacher.getUser().setAccountNonLocked(false);
        teacher.setUpdated_at(LocalDateTime.now());
        teacherRepository.save(teacher);
    }

    public TeacherDTO getTeacherByEmail(String email) {
        var teacher = teacherRepository.findTeacherByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Not such teacher with id :" + email));
        return converter.convertTeacherToDTO(teacher);
    }
}
