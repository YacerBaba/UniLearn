package com.yacer.unilearn.student;

import com.yacer.unilearn.entities.Level;
import com.yacer.unilearn.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select s from Student s where s.user.id = :user_id")
    Student findByUserId(@PathVariable int user_id);

    List<Student> findStudentsByCurrentEnrollment_Level(Level level);
}
