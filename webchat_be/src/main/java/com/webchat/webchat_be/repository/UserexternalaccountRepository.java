package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userexternalaccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserexternalaccountRepository extends JpaRepository<Userexternalaccount, Integer>, JpaSpecificationExecutor<Userexternalaccount> {

}