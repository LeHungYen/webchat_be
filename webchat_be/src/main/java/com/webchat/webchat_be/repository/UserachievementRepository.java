package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userachievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserachievementRepository extends JpaRepository<Userachievement, Integer>, JpaSpecificationExecutor<Userachievement> {

}