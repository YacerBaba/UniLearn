package com.yacer.unilearn.teacher;
import com.yacer.unilearn.entities.Teacher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    public List<Teacher> findAll(int page_number) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page_number, 10, sort);
        return teacherRepository.findAll(pageable).getContent();
    }

    public Teacher findByUserId(int user_id) {
        return teacherRepository.findByUserId(user_id);
    }
}
