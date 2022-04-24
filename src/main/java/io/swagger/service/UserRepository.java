package io.swagger.service;

import io.swagger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    @Modifying
    @Query("select u from User u where u.email = ?1")
    User findUserByEmail(String email);
}
