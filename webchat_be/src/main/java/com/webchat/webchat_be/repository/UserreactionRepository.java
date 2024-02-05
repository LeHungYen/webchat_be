package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userreaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserreactionRepository extends JpaRepository<Userreaction, Integer>, JpaSpecificationExecutor<Userreaction> {

}