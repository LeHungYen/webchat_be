package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userwall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserwallRepository extends JpaRepository<Userwall, Integer>, JpaSpecificationExecutor<Userwall> {

}