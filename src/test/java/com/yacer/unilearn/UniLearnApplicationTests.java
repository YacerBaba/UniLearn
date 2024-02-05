package com.yacer.unilearn;

import com.yacer.unilearn.student.StudentService;
import com.yacer.unilearn.utils.ApplicationUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UniLearnApplicationTests {
    @Autowired
    private StudentService studentService;

    @Test
    public void getStudentsByLevel() {
        var students = studentService.findStudentsByLevel("L1");
    }

    @Test
    public void getStudentById() {
        var studentDto = studentService.getStudentById(1);
        System.out.println(studentDto);
    }
}
