package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PollRepository extends JpaRepository<Poll, Integer>, JpaSpecificationExecutor<Poll> {

}