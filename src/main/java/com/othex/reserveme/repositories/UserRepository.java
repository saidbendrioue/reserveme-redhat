package com.othex.reserveme.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.othex.reserveme.entities.User;

@Component
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String username);
}

