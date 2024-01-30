package com.yacer.unilearn.auth.repositories;

import com.yacer.unilearn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);

    @Query("select u from User u where u.refreshToken.token = :token")
    Optional<User> findUserByRefreshToken(@PathVariable String token);

}
