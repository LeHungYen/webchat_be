package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    Optional<User> getUserByEmailAndPassword (String email , String password);
    Optional<User> findByEmail(String email);
}