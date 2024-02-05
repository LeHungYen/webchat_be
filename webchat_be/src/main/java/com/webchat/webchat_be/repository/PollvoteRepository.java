package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Pollvote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PollvoteRepository extends JpaRepository<Pollvote, Integer>, JpaSpecificationExecutor<Pollvote> {

}