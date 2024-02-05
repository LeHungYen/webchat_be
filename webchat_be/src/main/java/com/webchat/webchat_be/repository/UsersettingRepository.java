package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Usersetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersettingRepository extends JpaRepository<Usersetting, Integer>, JpaSpecificationExecutor<Usersetting> {

}