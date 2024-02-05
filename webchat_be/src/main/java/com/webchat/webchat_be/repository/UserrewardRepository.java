package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userreward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserrewardRepository extends JpaRepository<Userreward, Integer>, JpaSpecificationExecutor<Userreward> {

}