package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Eventattendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventattendeeRepository extends JpaRepository<Eventattendee, Integer>, JpaSpecificationExecutor<Eventattendee> {

}