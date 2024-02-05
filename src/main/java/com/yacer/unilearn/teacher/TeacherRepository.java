package com.yacer.unilearn.teacher;

import com.yacer.unilearn.entities.Student;
import com.yacer.unilearn.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("select t from Teacher t where t.user.id = :user_id")
    Teacher findByUserId(@PathVariable int user_id);

    @Query("select t from Teacher t where t.user.accountNonLocked = true ")
    List<Teacher> findAll();
}
