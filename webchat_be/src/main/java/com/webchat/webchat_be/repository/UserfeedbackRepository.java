package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userfeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserfeedbackRepository extends JpaRepository<Userfeedback, Integer>, JpaSpecificationExecutor<Userfeedback> {

}