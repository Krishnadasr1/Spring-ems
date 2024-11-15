package com.example.spring.project.repository;

import com.example.spring.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    Optional<User> findBypassword(String password);


    List<User> findByNameContainingIgnoreCase(String name);
}