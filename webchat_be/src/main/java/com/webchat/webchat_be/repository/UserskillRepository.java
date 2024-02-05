package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userskill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserskillRepository extends JpaRepository<Userskill, Integer>, JpaSpecificationExecutor<Userskill> {

}