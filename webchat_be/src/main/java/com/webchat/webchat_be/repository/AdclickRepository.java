package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Adclick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdclickRepository extends JpaRepository<Adclick, Integer>, JpaSpecificationExecutor<Adclick> {

}