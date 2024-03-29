package com.yacer.unilearn.student.repositories;

import com.yacer.unilearn.entities.Level;
import com.yacer.unilearn.entities.Student;
import com.yacer.unilearn.enums.LevelEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select s from Student s where s.user.id = :user_id")
    Student findByUserId(@PathVariable int user_id);

    @Query("select s from Student s where s.user.email = :email")
    Optional<Student> findByEmail(@PathVariable String email);

    @Query("select s from Student s where s.currentEnrollment.level.name = :level and s.user.accountNonLocked = true")
    List<Student> findStudentsByCurrentLevel(LevelEnum level);

    @Query("select s from Student s where s.user.accountNonLocked = true ")
    List<Student> findAll();
}
