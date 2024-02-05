package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userlanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserlanguageRepository extends JpaRepository<Userlanguage, Integer>, JpaSpecificationExecutor<Userlanguage> {

}