package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
