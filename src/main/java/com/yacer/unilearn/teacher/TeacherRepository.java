package com.yacer.unilearn.teacher;

import com.yacer.unilearn.entities.Student;
import com.yacer.unilearn.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("select t from Teacher t where t.user.email = :email")
    Optional<Teacher> findTeacherByEmail(@PathVariable String email);

    @Query("select t from Teacher t where t.user.accountNonLocked = true ")
    List<Teacher> findAll();
}
