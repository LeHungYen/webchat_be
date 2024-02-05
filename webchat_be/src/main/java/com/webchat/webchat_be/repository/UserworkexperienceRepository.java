package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userworkexperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserworkexperienceRepository extends JpaRepository<Userworkexperience, Integer>, JpaSpecificationExecutor<Userworkexperience> {

}