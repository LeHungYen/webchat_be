package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userfollowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserfollowingRepository extends JpaRepository<Userfollowing, Integer>, JpaSpecificationExecutor<Userfollowing> {

}