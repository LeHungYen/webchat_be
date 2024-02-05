package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Usereducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsereducationRepository extends JpaRepository<Usereducation, Integer>, JpaSpecificationExecutor<Usereducation> {

}