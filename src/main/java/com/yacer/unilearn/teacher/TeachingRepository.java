package com.yacer.unilearn.teacher;

import com.yacer.unilearn.entities.Teaches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachingRepository extends JpaRepository<Teaches, Integer> {
}
