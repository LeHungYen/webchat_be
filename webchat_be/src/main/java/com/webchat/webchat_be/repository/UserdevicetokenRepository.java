package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Userdevicetoken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserdevicetokenRepository extends JpaRepository<Userdevicetoken, Integer>, JpaSpecificationExecutor<Userdevicetoken> {

}