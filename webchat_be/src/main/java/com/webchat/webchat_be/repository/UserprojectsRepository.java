package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userprojects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserprojectsRepository extends JpaRepository<Userprojects, Integer>, JpaSpecificationExecutor<Userprojects> {

}