package com.yacer.unilearn.student;

import com.yacer.unilearn.entities.Student;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public List<Student> findAll(int page_number) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page_number, 10, sort);
        return studentRepository.findAll(pageable).getContent();
    }

    public Student findByUserId(int user_id) {
        return studentRepository.findByUserId(user_id);
    }
}
