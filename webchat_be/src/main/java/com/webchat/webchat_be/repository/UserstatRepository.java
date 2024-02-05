package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userstat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserstatRepository extends JpaRepository<Userstat, Integer>, JpaSpecificationExecutor<Userstat> {

}