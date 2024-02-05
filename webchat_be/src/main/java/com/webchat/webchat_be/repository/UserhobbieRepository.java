package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userhobbie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserhobbieRepository extends JpaRepository<Userhobbie, Integer>, JpaSpecificationExecutor<Userhobbie> {

}