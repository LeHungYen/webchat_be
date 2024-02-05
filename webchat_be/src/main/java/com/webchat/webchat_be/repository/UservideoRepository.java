package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Uservideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UservideoRepository extends JpaRepository<Uservideo, Integer>, JpaSpecificationExecutor<Uservideo> {

}